package com.github.theimplementer.valkyrie.sector;

import java.util.Arrays;

import static com.github.theimplementer.valkyrie.sector.SectorDefinition.*;

public abstract class Sector {

    private final byte[] sectorData;

    public Sector(byte[] sectorData) {
        this.sectorData = Arrays.copyOf(sectorData, sectorData.length);
    }

    public byte getMinutes() {
        return sectorData[MINUTES];
    }

    public byte getSeconds() {
        return sectorData[SECONDS];
    }

    public byte getSectors() {
        return sectorData[SECTORS];
    }

    public byte getMode() {
        return sectorData[MODE];
    }

    public abstract byte[] getData();
}
