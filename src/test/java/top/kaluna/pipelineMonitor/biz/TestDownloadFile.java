//package top.kaluna.pipelineMonitor.biz;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import top.kaluna.pipelineMonitor.PipelineMonitorApplication;
//import top.kaluna.pipelineMonitor.service.FileUploadAndDownloadService;
//import top.kaluna.pipelineMonitor.util.FileUtil;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//
///**
// * @author Yuery
// * @date 2022/5/20/0020 - 9:23
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = PipelineMonitorApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Slf4j
//public class TestDownloadFile {
//
//    @Resource
//    private FileUploadAndDownloadService fileUploadService;
//    @Test
//    public void testDownloadFile(){
//        fileUploadService.fileDownload();
//    }
//    @Test
//    public void testListFileNames(){
//        System.out.println(fileUploadService.getOSSFileNames("2022/5/20"));
//    }
//    @Test
//    public void testDeleteFileFromOss(){
//        fileUploadService.deleteFileFromOss();
//    }
//    @Test
//    public void testDeleteFileFromLocal1() throws IOException {
//        FileUtil.deleteSaveFileDir1("D:/desktop/demandAnalysis/downloadfile/");
//    }
//
//    @Test
//    public void testDeleteFileFromLocal2() throws IOException {
//        //这个方法里面自动添加了2022/05/20 目录
//        fileUploadService.deleteFileFromLocal();
//    }
//    @Test
//    public void createLocalDir(){
//        FileUtil.createSaveFileDir("D:/desktop/demandAnalysis/downloadfile/2022/05/20/");
//    }
//}
