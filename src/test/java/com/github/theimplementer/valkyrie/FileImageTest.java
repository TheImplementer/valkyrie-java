package com.github.theimplementer.valkyrie;

import com.github.theimplementer.valkyrie.sector.Sector;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Spliterator;
import java.util.stream.Stream;

import static com.github.theimplementer.valkyrie.sector.SectorDefinition.MODE;
import static com.github.theimplementer.valkyrie.sector.SectorDefinition.SECTOR_SIZE;
import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.delete;
import static java.util.Spliterator.NONNULL;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileImageTest {

    private Path tempFilePath;

    @Before
    public void setupTempFile() throws IOException {
        tempFilePath = createTempFile("", "");
    }

    @After
    public void deleteTempFile() throws IOException {
        delete(tempFilePath);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getSectorShouldThrowAnExceptionWithANegativeSectorNumber() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        final FileImage fileImage = new FileImage(tempFilePath.toFile());
        fileImage.getSector(-1);
    }

    @Test
    public void getSectorShouldThrowAnExceptionWithAnInvalidSectorNumber() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        final FileImage fileImage = new FileImage(tempFilePath.toFile());
        fileImage.getSector(0);
    }

    @Test
    public void getSectorShouldReturnTheExpectedSector() throws Exception {
        writeInto(tempFilePath, simpleSector());
        final FileImage fileImage = new FileImage(tempFilePath.toFile());

        final Sector sector = fileImage.getSector(0);

        assertThat(sector, notNullValue());
    }

    @Test
    public void sectorCountShouldReturnTheNumberOfSectors() throws Exception {
        writeInto(tempFilePath, simpleSector());
        writeInto(tempFilePath, simpleSector());
        final FileImage fileImage = new FileImage(tempFilePath.toFile());

        final long sectorCount = fileImage.sectorCount();

        assertThat(sectorCount, is(2L));
    }

    @Test
    public void shouldBeAbleToIterateOverAFileImageSectors() throws Exception {
        writeInto(tempFilePath, simpleSector());
        writeInto(tempFilePath, simpleSector());
        final FileImage fileImage = new FileImage(tempFilePath.toFile());

        final Spliterator<Sector> sectorSpliterator = spliteratorUnknownSize(fileImage.iterator(), NONNULL);
        final Stream<Sector> sectorStream = stream(sectorSpliterator, false);

        assertThat(sectorStream.count(), is(2L));
    }

    private void writeInto(Path path, byte[] bytes) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(path.toFile(), true);
        fileOutputStream.write(bytes);
    }

    private byte[] simpleSector() {
        final byte[] sectorData = new byte[SECTOR_SIZE];
        sectorData[MODE] = 2;
        return sectorData;
    }
}