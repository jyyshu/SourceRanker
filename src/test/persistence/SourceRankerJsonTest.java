package persistence;

import model.Source;

import static org.junit.jupiter.api.Assertions.assertEquals;

// persistence features largely based on JsonSerializationDemo; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class SourceRankerJsonTest {
    protected void checkSourceTest(String ident, String title, String body, int rank, Source source) {
        assertEquals(ident, source.getIdentifier());
        assertEquals(title, source.getTitle());
        assertEquals(body, source.getBody());
        assertEquals(rank, source.getRank());
    }
}


