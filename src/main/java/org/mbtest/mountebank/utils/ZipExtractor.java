package org.mbtest.mountebank.utils;

import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import java.io.File;
import java.io.IOException;

public class ZipExtractor {
    public void extract(File inputFile, File targetDirectory) throws IOException {
        Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.ZIP);
        archiver.extract(inputFile, targetDirectory);
    }
}
