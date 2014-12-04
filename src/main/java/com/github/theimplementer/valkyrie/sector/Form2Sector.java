package com.github.theimplementer.valkyrie.sector;

import static com.github.theimplementer.valkyrie.sector.Form.FORM2;

public class Form2Sector extends Sector {

    public Form2Sector(byte[] sectorData) {
        super(sectorData);
    }

    @Override
    public byte[] getData() {
        return new byte[0];
    }

    @Override
    public Form getForm() {
        return FORM2;
    }
}
