package org.mbtest.mountebank.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//Algorithm lifted from: http://www.codejava.net/java-se/file-io/programmatically-extract-a-zip-file-using-java
public class ZipExtractor {
    public static void extract(ZipInputStream zipInputStream, File targetDirectory) throws IOException {
        targetDirectory.mkdir();

        ZipEntry archiveEntry;
        while ((archiveEntry = zipInputStream.getNextEntry()) != null) {
            String newFilePath = targetDirectory.getPath() + File.separator + archiveEntry.getName();
            if (!archiveEntry.isDirectory()) {
                extractFile(zipInputStream, newFilePath);
            } else {
                createDirectory(newFilePath);
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
    }

    private static boolean createDirectory(String filePath) {
        return new File(filePath).mkdir();
    }

    private static void extractFile(ZipInputStream zipInputStream, String filePath) throws IOException {
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] buffer = new byte[4096];

        int bytesRead;
        while ((bytesRead = zipInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.close();
    }
}
