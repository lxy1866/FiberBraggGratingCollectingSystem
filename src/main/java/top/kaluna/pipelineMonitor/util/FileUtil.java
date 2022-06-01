package top.kaluna.pipelineMonitor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author Yuery
 * @date 2022/5/19/0019 - 12:30
 */
public class FileUtil {

    /**
     * 获取昨天的文件路径
     * @return 例如 2022/5/20
     */
    public  static String getYesterdayFolder(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.plusDays(-1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        // 2022/5/20
        return dtf.format(yesterday);
    }
    /**
     * 获取今天的文件路径
     * @return 例如 2022/5/20
     */
    public  static String getTodayFolder(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        // 2022/5/20
        return dtf.format(now);
    }

    public static boolean createSaveFileDir(String path){
        File directory = new File(path);
        //mkdir
        return directory.mkdirs();
    }
    public static void deleteSaveFileDir2(String path) throws IOException {

        File file = new File(path);
        if(!file.exists() || !file.isDirectory()){
            return;
        }
        String[] tempList = file.list();
        assert tempList != null;
        for (String s : tempList) {
            File temp = new File(path + File.separator + s);
            temp.delete();
        }
    }
    public static void deleteSaveFileDir1(String basePath) throws IOException {
        Path path = Paths.get(basePath);
        Files.walkFileTree(path,
                new SimpleFileVisitor<Path>() {
                    // 先去遍历删除文件
                    @Override
                    public FileVisitResult visitFile(Path file,
                                                     BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        System.out.printf("文件被删除 : %s%n", file);
                        return FileVisitResult.CONTINUE;
                    }
                    // 再去遍历删除目录
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir,
                                                              IOException exc) throws IOException {
                        Files.delete(dir);
                        System.out.printf("文件夹被删除: %s%n", dir);
                        return FileVisitResult.CONTINUE;
                    }

                }
        );
    }
}
