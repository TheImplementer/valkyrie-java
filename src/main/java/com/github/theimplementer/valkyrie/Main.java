package com.github.theimplementer.valkyrie;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        final DiscImage discImage = new DiscImage(new File("/Users/implementer/Documents/vp.bin"));
        System.out.println("discImage.sectorCount() = " + discImage.sectorCount());
        System.out.println("discImage.getSector(0) = " + discImage.getSector(0));
        final byte[] sectorData = discImage.getSector(0).getData();
        System.out.println("discImage = " + Arrays.toString(sectorData));
    }
}
