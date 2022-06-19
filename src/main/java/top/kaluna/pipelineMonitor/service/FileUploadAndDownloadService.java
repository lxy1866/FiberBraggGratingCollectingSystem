package top.kaluna.pipelineMonitor.service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.kaluna.pipelineMonitor.config.FileConfig;
import top.kaluna.pipelineMonitor.config.OSSConfig;
import top.kaluna.pipelineMonitor.util.FileUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/5/17/0017 - 12:01
 */
@Service
@Slf4j
public class FileUploadAndDownloadService {

    @Resource
    private OSSConfig ossConfig;
    @Resource
    private FileConfig fileConfig;
    public String fileUploadFileWeb(MultipartFile file){
        //获取相关配置
        String bucketname = ossConfig.getBucketname();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        //创建OSS对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //获取原生文件名
        String originalFileName = file.getOriginalFilename();

        //JDK8的日期格式
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //拼装路径,oss上存储的路径  data/2022/5/20/log2022-5-9-16-43-55.xlsx
        String folder = dtf.format(ldt);

        // 在OSS上的bucket下创建 data 这个文件夹
        String newFileName = "data/"+folder+"/"+originalFileName;

        try {
            PutObjectResult putObjectResult = ossClient.putObject(bucketname,newFileName,file.getInputStream());
            //拼装返回路径
            if(putObjectResult != null){
                String fileUrl = "https://"+bucketname+"."+endpoint+"/"+newFileName;
                return fileUrl;
            }
        } catch (IOException e) {
            log.error("文件上传失败:{}",e);
        } finally {
            //oss关闭服务，不然会造成OOM
            ossClient.shutdown();
        }
        return null;
    }

    /**
     * 下载oss文件
     * @return 所有文件的完整保存路径
     */
    public List<String> fileDownload(String folder){
        String saveFilePath = fileConfig.getPath();
        //获取相关配置
        String bucketname = ossConfig.getBucketname();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        // 2022/5/20

        //获取昨天文件的所有文件名
        final List<String> ossFileNames = getOSSFileNames(folder);
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String prefix = "data/" + folder + "/";
        List<String> completeSaveFilePathList = new ArrayList<>();
        try {
            ossFileNames.forEach((objectName) -> {
                objectName = prefix + objectName.split("/")[4];
                System.out.println(objectName);
                // 请求10个任务并发下载。
                DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketname, objectName);
                // 指定Object下载到本地文件的完整路径，例如D:\desktop\demandAnalysis\downloadfile\examplefile.txt。
                FileUtil.createSaveFileDir(fileConfig.getPath()+folder+"/");
                String completeSaveFilePath = fileConfig.getPath() +folder+"/" + objectName.split("/")[4];
                downloadFileRequest.setDownloadFile(completeSaveFilePath);
                completeSaveFilePathList.add(completeSaveFilePath);
                // 设置分片大小，单位为字节，取值范围为100 KB~5 GB。默认值为100 KB。
                downloadFileRequest.setPartSize(1024 * 1024);
                // 设置分片下载的并发数，默认值为1。
                downloadFileRequest.setTaskNum(5);
                // 开启断点续传下载，默认关闭。
                downloadFileRequest.setEnableCheckpoint(true);
                // 只有当Object下载中断产生了断点记录文件后，如果需要继续下载该Object，才需要设置对应的断点记录文件。下载完成后，该文件会被删除。
                downloadFileRequest.setCheckpointFile(saveFilePath+"yourCheckpointFile");
                // 下载文件
                DownloadFileResult downloadRes = null;
                try {
                    downloadRes = ossClient.downloadFile(downloadFileRequest);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                // 下载成功时，会返回文件元信息
                ObjectMetadata objectMetadata = downloadRes.getObjectMetadata();
                System.out.println(objectMetadata.getETag());
                System.out.println(objectMetadata.getLastModified());
                System.out.println(objectMetadata.getUserMetadata().get("meta"));
            });
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (Throwable ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            //ce.printStackTrace();
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return completeSaveFilePathList;
    }


    /**
     * 获取所有文件名
     * @param folder 例如 2022/5/20
     * @return 返回指定日期的所有文件名
     */
    public List<String> getOSSFileNames(String folder){
        List<String> names = new ArrayList<>();
        //获取相关配置
        String bucketname = ossConfig.getBucketname();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        // 指定前缀，例如data/2022/05/20。昨天的数据
        String keyPrefix = "data/" + folder;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 列举文件。如果不设置keyPrefix，则列举存储空间下的所有文件。如果设置keyPrefix，则列举包含指定前缀的文件。
            ObjectListing objectListing = ossClient.listObjects(bucketname, keyPrefix);
            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                names.add(s.getKey());
            }
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return names;
    }

    /**
     * 删除昨天的文件
     * @return 是否删除完成
     */
    public boolean deleteFileFromOss(String folder){
        //获取相关配置
        String bucketname = ossConfig.getBucketname();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();

        //拼装路径,oss上存储的路径 2022/5/20

        //获取昨天文件的所有文件名
        final List<String> ossFileNames = getOSSFileNames(folder);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String prefix = "data/" + folder + "/";
        try {
            ossFileNames.forEach((objectName)->{
                objectName = prefix + objectName.split("/")[4];
                // 删除文件或目录。如果要删除目录，目录必须为空。
                ossClient.deleteObject(bucketname, objectName);
            });
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return true;
    }
    /**
     * 删除昨天
     * @return 是否删除完成
     */
    public boolean deleteFileFromLocal(String folder) throws IOException {
        final String basePath = fileConfig.getPath();
        //D:/desktop/demandAnalysis/downloadfile/
        String completerPath = basePath + folder +"/";
        FileUtil.deleteSaveFileDir1(completerPath);
        return true;
    }
}
