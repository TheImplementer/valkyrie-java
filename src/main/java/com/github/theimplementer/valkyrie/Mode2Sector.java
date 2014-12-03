package com.github.theimplementer.valkyrie;

public class Mode2Sector extends Sector {

    public Mode2Sector(byte[] sectorData) {
        super(sectorData);
    }

    @Override
    public byte[] getData() {
        return new byte[0];
    }
}
