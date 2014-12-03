package com.github.theimplementer.valkyrie.sector;

public class Form2Sector extends Sector {

    public Form2Sector(byte[] sectorData) {
        super(sectorData);
    }

    @Override
    public byte[] getData() {
        return new byte[0];
    }
}
