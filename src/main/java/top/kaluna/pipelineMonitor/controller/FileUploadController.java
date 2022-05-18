package top.kaluna.pipelineMonitor.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.kaluna.pipelineMonitor.resp.FileResp;
import top.kaluna.pipelineMonitor.service.FileUploadService;

import javax.annotation.Resource;

/**
 * @author Yuery
 * @date 2022/5/17/0017 - 11:43
 */
@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {
    @Resource
    FileUploadService fileUploadService;
    /**
     * 上传文件到oss
     * @param file 文件
     * @return url
     */
    @PostMapping(value = "uploadOneFile")
    public FileResp uploadOneFile(@RequestPart("file") MultipartFile file){
        String result = fileUploadService.fileUploadOneFile(file);

        FileResp fileResp = new FileResp();
        fileResp.setUrl(result);
        fileResp.setName(file.getName());
        fileResp.setResponse("{status:success}");
        fileResp.setStatus("done");
        return fileResp;
    }
//    /**
//     * 上传文件到oss
//     * @param file 文件
//     * @return url
//     */
//    @PostMapping(value = "uploadOneFile")
//    public FileResp uploadFolder(@RequestPart("file") MultipartFile file){
//        String result = fileUploadService.fileUploadOneFile(file);
//
//        FileResp fileResp = new FileResp();
//        fileResp.setUrl(result);
//        fileResp.setName(file.getName());
//        fileResp.setResponse("{status:success}");
//        fileResp.setStatus("done");
//        return fileResp;
//    }


}
