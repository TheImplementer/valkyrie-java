package com.github.theimplementer.valkyrie;

import com.github.theimplementer.valkyrie.sector.Sector;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        final FileImage fileImage = new FileImage(new File("/Users/implementer/Documents/vp.bin"));
        for (Sector sector : fileImage) {
            System.out.println("sector.getMode() = " + sector.getMode());
            Thread.sleep(400);
        }
    }
}
