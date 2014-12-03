package com.github.theimplementer.valkyrie;

import com.github.theimplementer.valkyrie.sector.Sector;

import java.io.*;
import java.util.Iterator;

import static com.github.theimplementer.valkyrie.sector.SectorFactory.sector;

public class FileImage implements Iterable<Sector> {

    private final BufferedInputStream inputStream;

    public FileImage(File file) throws FileNotFoundException {
        this.inputStream = new BufferedInputStream(new FileInputStream(file));
    }

    @Override
    public Iterator<Sector> iterator() {
        try {
            return new SectorIterator(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while creating the sector iterator.", e);
        }
    }

    private static class SectorIterator implements Iterator<Sector> {

        private static final int SECTOR_SIZE = 2352;

        private final BufferedInputStream inputStream;
        private final byte[] buffer;
        private int numberOfBytesRead;

        private SectorIterator(BufferedInputStream inputStream) throws IOException {
            this.inputStream = inputStream;
            this.buffer = new byte[SECTOR_SIZE];
            this.numberOfBytesRead = inputStream.read(buffer);
        }

        @Override
        public boolean hasNext() {
            return numberOfBytesRead > 0;
        }

        @Override
        public Sector next() {
            final Sector sector = sector(buffer);
            try {
                numberOfBytesRead = inputStream.read(buffer);
            } catch (IOException e) {
                throw new RuntimeException("An error occurred while reading the next sector from the file.", e);
            }
            return sector;
        }
    }
}
