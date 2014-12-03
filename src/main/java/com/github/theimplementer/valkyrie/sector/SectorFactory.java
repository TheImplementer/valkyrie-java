package com.github.theimplementer.valkyrie.sector;

import static com.github.theimplementer.valkyrie.sector.SectorDefinition.FORM_MASK;
import static com.github.theimplementer.valkyrie.sector.SectorDefinition.MODE;
import static java.lang.String.format;

public class SectorFactory {

    private static final int SUPPORTED_MODE = 2;

    public static Sector sector(byte sectorData[]) {
        assertValidSectorSize(sectorData);
        final byte sectorMode = sectorData[MODE];
        assertModeIsSupported(sectorMode);

        final int formBit = sectorData[SectorDefinition.SUBHEADER] & FORM_MASK;

        return formBit == 0 ? new Form1Sector(sectorData) : new Form2Sector(sectorData);
    }

    private static void assertModeIsSupported(byte sectorMode) {
        if (sectorMode != SUPPORTED_MODE) {
            throw new IllegalArgumentException(format("Unsupported mode: %s", sectorMode));
        }
    }

    private static void assertValidSectorSize(byte[] sectorData) {
        if (sectorData.length != SectorDefinition.SECTOR_LENGTH) {
            throw new IllegalArgumentException("The size of the specified sector is invalid.");
        }
    }
}
