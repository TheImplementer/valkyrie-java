package com.github.theimplementer.valkyrie.sector;

public class SectorDefinition {
    public static final int SECTOR_LENGTH = 2352;

    public static final int MINUTES = 12;
    public static final int SECONDS = 13;
    public static final int SECTORS = 12;
    public static final int MODE = 15;
    public static final int SUBHEADER = 18;
    public static final int DATA_START = 24;

    public static final byte FORM_MASK = 0x20;
    public static final byte DATA_MASK = 0x8;
    public static final byte AUDIO_MASK = 0x4;
    public static final byte VIDEO_MASK = 0x2;
}
