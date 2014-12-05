package com.github.theimplementer.valkyrie;

import com.github.theimplementer.valkyrie.sector.Sector;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

import static com.github.theimplementer.valkyrie.sector.SectorDefinition.SECTOR_SIZE;
import static com.github.theimplementer.valkyrie.sector.SectorFactory.sector;

public class FileSectorIterator implements Iterator<Sector> {

    private final RandomAccessFile randomAccessFile;
    private final byte[] buffer;
    private int numberOfBytesRead;

    public FileSectorIterator(File file) throws IOException {
        this.randomAccessFile = new RandomAccessFile(file, "r");
        this.buffer = new byte[SECTOR_SIZE];
        this.numberOfBytesRead = randomAccessFile.read(buffer);
    }

    @Override
    public boolean hasNext() {
        return numberOfBytesRead > 0;
    }

    @Override
    public Sector next() {
        final Sector sector = sector(buffer);
        try {
            numberOfBytesRead = randomAccessFile.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading the next sector from the file.", e);
        }
        return sector;
    }
}
