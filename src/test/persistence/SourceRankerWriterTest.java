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

public class SourceRankerWriterTest extends SourceRankerJsonTest {

    @Test
    void sourceRankerWriterExceptionTest() {
        try {
            SourceRanker sr = new SourceRanker();
            SourceRankerWriter sourceRankerWriter = new SourceRankerWriter("./data/lkjds-234'=]aj\0di:;.,.json");
            sourceRankerWriter.openWriter();
            fail("An IOEception was expected");
        } catch (IOException ioe) {
        }
    }

    @Test
    void sourceRankingWriterEmptyTest() {
        try {
            SourceRanker sr = new SourceRanker();
            SourceRankerWriter sourceRankerWriter = new SourceRankerWriter("./data/emptySourceRankerTest.json");
            sourceRankerWriter.openWriter();
            sourceRankerWriter.write(sr);
            sourceRankerWriter.quitWriter();

            SourceRankerReader sourceRankerReader = new SourceRankerReader("./data/emptySourceRankerTest.json");
            sr = sourceRankerReader.read();
            assertEquals(0, sr.sourceListSize());
        } catch (IOException ioe) {
            fail("Should not have thrown an IOException");
        }
    }

    @Test
    void sourceRankingWriterTest() {
        try {
            SourceRanker sr = new SourceRanker();
            sr.addSource((new Source("matcha", "Green Tea",
                    "Drinking green tea is good for you!", 100)));
            sr.addSource((new Source("cherry", "Hanami",
                    "Cherry Blossom viewing season is here", 7)));
            sr.addSource((new Source("abc", "Alphabet",
                    "abcdefghijklmnopqrstuvwxyz", 1)));
            SourceRankerWriter sourceRankerWriter = new SourceRankerWriter("./data/sourceRankerWriterTest.json");
            sourceRankerWriter.openWriter();
            sourceRankerWriter.write(sr);
            sourceRankerWriter.quitWriter();

            SourceRankerReader sourceRankerReader = new SourceRankerReader("./data/sourceRankerWriterTest.json");
            sr = sourceRankerReader.read();
            ArrayList<Source> sourcesList = sr.getSourceList();
            assertEquals(3, sourcesList.size());
            checkSourceTest("matcha", "Green Tea",
                    "Drinking green tea is good for you!", 100, sourcesList.get(0));
            checkSourceTest("cherry", "Hanami",
                    "Cherry Blossom viewing season is here", 7, sourcesList.get(1));
            checkSourceTest("abc", "Alphabet",
                    "abcdefghijklmnopqrstuvwxyz", 1, sourcesList.get(2));
        } catch (IOException ioe) {
            fail("Should not have thrown an IOException");
        }
    }
}
