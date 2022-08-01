package persistence;

import model.SourceRanker;
import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// persistence features largely based on JsonSerializationDemo; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// A representation of a JSON writer that writes a JSON version of a source ranker to file
public class SourceRankerWriter {
    private static int TAB = 4;
    private PrintWriter sourceRankerWriter;
    private String output;

    // EFFECTS: creates a source ranker writer to save sources to output file
    public SourceRankerWriter(String output) {
        this.output = output;
    }

    // MODIFIES: this
    // EFFECTS: opens the writer, and if output file can't be opened to be written; throws a
    //          FileNotFoundException in response
    public void openWriter() throws FileNotFoundException {
        sourceRankerWriter = new PrintWriter(new File(output));
    }

    // MODIFIES: this
    // EFFECTS: writes a JSON version of a source ranker to file
    public void write(SourceRanker sourceRanker) {
        JSONObject json = sourceRanker.toJson();
        fileSave(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: quits writer
    public void quitWriter() {
        sourceRankerWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: saves/writes given json string to file
    private void fileSave(String json) {
        sourceRankerWriter.print(json);
    }
}
