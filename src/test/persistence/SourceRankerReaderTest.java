package persistence;

import model.Source;
import model.SourceRanker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// persistence features largely based on JsonSerializationDemo; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class SourceRankerReaderTest extends SourceRankerJsonTest {

    @Test
    void testSourceRankerReaderFileFound() {
        SourceRankerReader sourceRankerReader = new SourceRankerReader("./data/sourceRankerReaderTest.json");
        try {
            SourceRanker sr = sourceRankerReader.read();
            ArrayList<Source> sourcesList = sr.getSourceList();
            assertEquals(6, sourcesList.size());
            checkSourceTest("Cherry Blossom", "Cherry Blossoms in Japan ", "Hanami cherry blossom spring ", 3,
                    sourcesList.get(5));
        } catch (IOException ioe) {
            fail("Could not read data from file");
        }
    }

    @Test
    void testSourceRankerReaderNoFileFound() {
        SourceRankerReader sourceRankerReader = new SourceRankerReader("./data/abcdef.json");
        try {
            SourceRanker sr = sourceRankerReader.read();
            fail("An IOException is expected");
        } catch (IOException ioe) {
        }
    }
}