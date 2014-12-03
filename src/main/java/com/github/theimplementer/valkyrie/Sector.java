package com.github.theimplementer.valkyrie;

import java.util.Arrays;

public abstract class Sector {

    public static final int SECTOR_MODE_OFFSET = 15;

    private final byte[] sectorData;

    public Sector(byte[] sectorData) {
        this.sectorData = Arrays.copyOf(sectorData, sectorData.length);
    }

    public byte getMode() {
        return sectorData[SECTOR_MODE_OFFSET];
    }

    public abstract byte[] getData();
}
