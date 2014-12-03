package com.github.theimplementer.valkyrie;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.github.theimplementer.valkyrie.SectorFactory.SECTOR_LENGTH;
import static com.github.theimplementer.valkyrie.SectorFactory.sector;
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
        final byte[] sectorData = new byte[SECTOR_LENGTH];
        sectorData[Sector.SECTOR_MODE_OFFSET] = 0x7f;
        sector(sectorData);
    }

    @Test
    public void sectorShouldCreateAMode2SectorAsExpected() {
        final byte[] sectorData = new byte[SECTOR_LENGTH];
        sectorData[Sector.SECTOR_MODE_OFFSET] = 0x2;

        final Sector sector = sector(sectorData);

        assertThat(sector, notNullValue());
        assertThat(sector.getMode(), is((byte) 0x2));
    }

}