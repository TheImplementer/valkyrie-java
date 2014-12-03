package com.github.theimplementer.valkyrie;

import static com.github.theimplementer.valkyrie.Sector.SECTOR_MODE_OFFSET;

public class SectorFactory {

    public static final int SECTOR_LENGTH = 2352;

    public static Sector sector(byte sectorData[]) {
        if (sectorData.length != SECTOR_LENGTH) {
            throw new IllegalArgumentException("The size of the specified sector is invalid.");
        }
        final byte sectorMode = sectorData[SECTOR_MODE_OFFSET];
        switch (sectorMode) {
            case 0x2:
                return new Mode2Sector(sectorData);
            default:
                throw new IllegalArgumentException("The specified sector is invalid.");
        }
    }
}
