package com.github.theimplementer.valkyrie.sector;

import static com.github.theimplementer.valkyrie.sector.Form.FORM1;

public class Form1Sector extends Sector {

    public Form1Sector(byte[] sectorData) {
        super(sectorData);
    }

    @Override
    public byte[] getData() {
        return new byte[0];
    }

    @Override
    public Form getForm() {
        return FORM1;
    }
}
