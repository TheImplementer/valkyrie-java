package com.github.theimplementer.valkyrie;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        final FileImage fileImage = new FileImage(new File("/Users/implementer/Documents/vp.bin"));
        System.out.println("fileImage.sectorCount() = " + fileImage.sectorCount());
        System.out.println("fileImage.getSector(0) = " + fileImage.getSector(0));
        final byte[] sectorData = fileImage.getSector(0).getData();
        System.out.println("fileImage = " + Arrays.toString(sectorData));
    }
}
