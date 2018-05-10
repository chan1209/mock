package controller;

import org.testng.annotations.Test;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by zejunweizj on 2017/5/26.
 */
public class ApkCheck {


    @Test
    public void check(){

        //第一步.解压缩apk包到制定的文件路径下.
        String relativelyPath=System.getProperty("user.dir");
        String targetPath = relativelyPath+"/src/Path/targetPath/";
        String apkPath = relativelyPath+"/src/path/apk/Mall-1.3.0-合入base前.apk";
        try {
            decompress(apkPath,targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void decompress(String zipPath, String targetPath) throws IOException  {
        File file = new File(zipPath);
        if (!file.isFile()) {
            throw new FileNotFoundException("file not exist!");
        }
        if (targetPath == null || "".equals(targetPath)) {
            targetPath = file.getParent();
        }
        targetPath = targetPath + file.getName();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> files = zipFile.entries();
        ZipEntry entry = null;
        File outFile = null;
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        while (files.hasMoreElements()) {
            entry = files.nextElement();
            outFile = new File(targetPath + File.separator  +entry.getName());
            // 如果条目为目录，则跳向下一个
            if(entry.isDirectory()){
                outFile.mkdirs();
                continue;
            }
            // 创建目录
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            // 创建新文件
            outFile.createNewFile();
            // 如果不可写，则跳向下一个条目
            if (!outFile.canWrite()) {
                continue;
            }
            try {
                bin = new BufferedInputStream(zipFile.getInputStream(entry));
                bout = new BufferedOutputStream(new FileOutputStream(outFile));
                byte[] buffer = new byte[1024];
                int readCount = -1;
                while ((readCount = bin.read(buffer)) != -1) {
                    bout.write(buffer, 0, readCount);
                }
            } finally {
                try {
                    bin.close();
                    bout.flush();
                    bout.close();
                } catch (Exception e) {}
            }
        }
    }


}
