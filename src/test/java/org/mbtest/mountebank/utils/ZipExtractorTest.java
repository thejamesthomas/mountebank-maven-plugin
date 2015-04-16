package org.mbtest.mountebank.utils;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.mockito.Mockito.*;

public class ZipExtractorTest {
    @Ignore
    @Test
    public void shouldExtractAZip() throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("src/test/resources/mountebank-v1.2.103-win-x64.zip"));
        File target = new File("target");

        ZipExtractor.extract(zipInputStream, target);
    }

    @Test
    public void shouldCreateANewTargetDirectory() throws IOException {
        File target = mock(File.class);

        ZipExtractor.extract(mock(ZipInputStream.class), target);

        verify(target, times(1)).mkdir();
    }

    @Test
    public void shouldCloseTheTargetZipInputStream() throws IOException {
        ZipInputStream zipInputStream = mock(ZipInputStream.class);

        ZipExtractor.extract(zipInputStream, mock(File.class));

        verify(zipInputStream, times(1)).close();
    }

    @Test
    public void shouldNotReadFromTheZipEntryFileWhenAZipEntryIsADirectory() throws IOException {
        ZipEntry archiveEntry = mock(ZipEntry.class);
        when(archiveEntry.isDirectory()).thenReturn(true);

        ZipInputStream zipInputStream = mock(ZipInputStream.class);
        when(zipInputStream.getNextEntry()).thenReturn(archiveEntry).thenReturn(null);

        ZipExtractor.extract(zipInputStream, mock(File.class));

        verify(zipInputStream, never()).read();
    }

    @Test
    public void shouldReadFromTheZipEntryFileWhenAZipEntryIsAFile() throws IOException {
        File target = mock(File.class);
        when(target.getPath()).thenReturn("target");

        ZipEntry archiveEntry = mock(ZipEntry.class);
        when(archiveEntry.isDirectory()).thenReturn(false);
        when(archiveEntry.getName()).thenReturn("mockFile");

        ZipInputStream zipInputStream = mock(ZipInputStream.class);
        when(zipInputStream.getNextEntry()).thenReturn(archiveEntry).thenReturn(null);
        when(zipInputStream.read(any(byte[].class))).thenReturn(-1);

        ZipExtractor.extract(zipInputStream, target);

        verify(zipInputStream, times(1)).read(any(byte[].class));
    }
}