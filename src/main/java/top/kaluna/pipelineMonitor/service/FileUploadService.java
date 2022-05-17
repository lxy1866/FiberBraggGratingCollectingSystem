package top.kaluna.pipelineMonitor.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import top.kaluna.pipelineMonitor.config.OSSConfig;
import top.kaluna.pipelineMonitor.resp.CommonResp;
import top.kaluna.pipelineMonitor.util.CommonUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Yuery
 * @date 2022/5/17/0017 - 12:01
 */
@Service
@Slf4j
public class FileUploadService {

    @Resource
    private OSSConfig ossConfig;
    public String fileUploadOneFile(MultipartFile file){
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

        //拼装路径,oss上存储的路径  data/2022/5/17/log2022-5-9-16-43-55.xlsx
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

}
