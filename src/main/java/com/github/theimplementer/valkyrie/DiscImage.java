package com.github.theimplementer.valkyrie;

import com.github.theimplementer.valkyrie.sector.Sector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

import static com.github.theimplementer.valkyrie.sector.SectorDefinition.SECTOR_SIZE;
import static com.github.theimplementer.valkyrie.sector.SectorFactory.sector;

public class DiscImage implements Disc {

    private final RandomAccessFile randomAccessFile;
    private final File file;

    public DiscImage(File file) throws FileNotFoundException {
        this.file = file;
        this.randomAccessFile = new RandomAccessFile(file, "r");
    }

    @Override
    public Sector getSector(int number) throws IOException {
        if (number < 0 || number >= sectorCount())
            throw new IllegalArgumentException("The sector specified is invalid.");
        final long sectorPosition = number * SECTOR_SIZE;
        randomAccessFile.seek(sectorPosition);
        final byte[] sectorBuffer = new byte[SECTOR_SIZE];
        randomAccessFile.read(sectorBuffer, 0, SECTOR_SIZE);
        return sector(sectorBuffer);
    }

    @Override
    public long sectorCount() throws IOException {
        return randomAccessFile.length() / SECTOR_SIZE;
    }

    @Override
    public Iterator<Sector> iterator() {
        try {
            return new FileSectorIterator(file);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while creating the sector iterator.", e);
        }
    }

}
