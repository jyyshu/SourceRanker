package persistence;

import model.Source;
import model.SourceRanker;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// persistence features largely based on JsonSerializationDemo; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// A representation of a reader that reads a source ranker derived from stored JSON data
public class SourceRankerReader {
    private String data;

    // EFFECTS: creates a Json reader from file
    public SourceRankerReader(String data) {
        this.data = data;
    }

    // EFFECTS: reads saved source ranker and returns contents,
    //          otherwise throws IOException if unable to read data
    public SourceRanker read() throws IOException {
        String sourceRankingData = readSourceFile(data);
        JSONObject jsonObject = new JSONObject(sourceRankingData);
        return parseSourceRanker(jsonObject);
    }

    // EFFECTS: parses and returns source ranker from JSON object
    private SourceRanker parseSourceRanker(JSONObject jsonObject) {
        // String ident = jsonObject.getString("ident");
        SourceRanker sourceRanker = new SourceRanker();
        addSources(sourceRanker, jsonObject);
        return sourceRanker;
    }

    // EFFECTS: reads and returns data file in string format
    private String readSourceFile(String data) throws IOException {
        StringBuilder sourceRankerBuilder = new StringBuilder();

        try (
                Stream<String> stream = Files.lines(Paths.get(data), StandardCharsets.UTF_8)) {
            stream.forEach(stm -> sourceRankerBuilder.append(stm));
        }
        return sourceRankerBuilder.toString();
    }

    // MODIFIES: sourceranker
    // EFFECCTS: parses sources and adds sources into sourceranker (from JSON object)
    private void addSources(SourceRanker sourceRanker, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("sources");
        for (Object json : jsonArray) {
            JSONObject nextSource = (JSONObject) json;
            addSource(sourceRanker, nextSource);
        }
    }

    // MODIFIES: sourceranker
    // EFFECTS: parses source and andds source into sourceranker (from JSON object)
    private void addSource(SourceRanker sourceRanker, JSONObject jsonObject) {
        String ident = jsonObject.getString("ident");
        String title = jsonObject.getString("title");
        String body = jsonObject.getString("body");
        int rank = jsonObject.getInt("rank");
        Source source = new Source(ident, title, body, rank);
        sourceRanker.addSource(source);
    }
}
