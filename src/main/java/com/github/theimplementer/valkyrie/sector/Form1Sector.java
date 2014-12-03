package com.github.theimplementer.valkyrie.sector;

public class Form1Sector extends Sector {

    public Form1Sector(byte[] sectorData) {
        super(sectorData);
    }

    @Override
    public byte[] getData() {
        return new byte[0];
    }
}
