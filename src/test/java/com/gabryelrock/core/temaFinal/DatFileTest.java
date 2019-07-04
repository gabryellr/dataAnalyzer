package com.gabryelrock.core.temaFinal;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;

public class DatFileTest {

    private DatFile datFile;

    @Before
    public void init(){
        datFile = new DatFile();
    }

    @Test
    public void fileExist() {
        File file = new File("file.dat");
        assertTrue(file.exists());
    }

    @Test
    public void readFile() {
        assertEquals(6, datFile.readFile().size());
    }

    @Test
    public void saveFile() {
        datFile.execute();
        File fileExist = new File("file.done.dat");
        assertTrue(fileExist.exists() && fileExist.canRead());
    }
}