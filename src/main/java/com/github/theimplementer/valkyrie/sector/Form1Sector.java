package com.github.theimplementer.valkyrie.sector;

import static com.github.theimplementer.valkyrie.sector.Form.FORM1;
import static com.github.theimplementer.valkyrie.sector.SectorDefinition.DATA_START;
import static java.util.Arrays.copyOfRange;

public class Form1Sector extends Sector {

    private static final int DATA_SIZE = 2048;

    public Form1Sector(byte[] sectorData) {
        super(sectorData);
    }

    @Override
    public byte[] getData() {
        return copyOfRange(sectorData, DATA_START, DATA_START + DATA_SIZE);
    }

    @Override
    public Form getForm() {
        return FORM1;
    }
}
