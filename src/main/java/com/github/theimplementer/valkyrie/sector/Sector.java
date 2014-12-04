package com.github.theimplementer.valkyrie.sector;

import java.util.Arrays;

import static com.github.theimplementer.valkyrie.sector.SectorDefinition.*;

public abstract class Sector {

    protected final byte[] sectorData;

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

    public boolean isData() {
        return (sectorData[SUBHEADER] & DATA_MASK) != 0;
    }

    public boolean isAudio() {
        return (sectorData[SUBHEADER] & AUDIO_MASK) != 0;
    }

    public boolean isVideo() {
        return (sectorData[SUBHEADER] & VIDEO_MASK) != 0;
    }

    public abstract byte[] getData();

    public abstract Form getForm();
}
