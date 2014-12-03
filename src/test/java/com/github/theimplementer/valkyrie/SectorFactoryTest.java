package com.github.theimplementer.valkyrie;

import com.github.theimplementer.valkyrie.sector.Form1Sector;
import com.github.theimplementer.valkyrie.sector.Form2Sector;
import com.github.theimplementer.valkyrie.sector.Sector;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.github.theimplementer.valkyrie.sector.SectorDefinition.*;
import static com.github.theimplementer.valkyrie.sector.SectorFactory.sector;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SectorFactoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void sectorShouldThrowAnExceptionIfTheSectorDataSizeIsInvalid() {
        expectedException.expect(IllegalArgumentException.class);
        sector(new byte[]{});
    }

    @Test
    public void sectorShouldThrowAnExceptionIfTheSpecifiedDataIsNotAValidSector() {
        expectedException.expect(IllegalArgumentException.class);
        final byte[] sectorData = sectorWithMode((byte) 0x7f);
        sector(sectorData);
    }

    @Test
    public void sectorShouldCreateForm1SectorsAsExpected() {
        final byte[] sectorData = sectorWithMode((byte) 0x2);

        final Sector sector = sector(sectorData);

        assertThat(sector, notNullValue());
        assertThat(sector, instanceOf(Form1Sector.class));
        assertThat(sector.getMode(), is((byte) 0x2));
    }

    @Test
    public void sectorShouldCreateForm2SectorsAsExpected() {
        final byte[] sectorData = sectorWithMode((byte) 0x2);
        sectorData[SUBHEADER] = FORM_MASK;

        final Sector sector = sector(sectorData);

        assertThat(sector, notNullValue());
        assertThat(sector, instanceOf(Form2Sector.class));
        assertThat(sector.getMode(), is((byte) 0x2));
    }

    private byte[] sectorWithMode(byte mode) {
        final byte[] sectorData = new byte[SECTOR_LENGTH];
        sectorData[MODE] = mode;
        return sectorData;
    }

}