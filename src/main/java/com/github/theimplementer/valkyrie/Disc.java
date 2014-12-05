package com.github.theimplementer.valkyrie;

import com.github.theimplementer.valkyrie.sector.Sector;

public interface Disc extends Iterable<Sector> {
    Sector getSector(int number) throws Exception;

    long sectorCount() throws Exception;
}
