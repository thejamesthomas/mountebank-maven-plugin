package org.mbtest.mountebank.utils;

import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;
import org.rauschig.jarchivelib.CompressionType;

import java.io.File;
import java.io.IOException;

public class TarGzExtractor {
    public static void extract(File inputFile, File targetDirectory) throws IOException {
        Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.GZIP);
        archiver.extract(inputFile, targetDirectory);
    }
}
