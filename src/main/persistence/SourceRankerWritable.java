package persistence;

import org.json.JSONObject;

// persistence features largely based on JsonSerializationDemo; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// EFFECTS: produces this as a JSON object
public interface SourceRankerWritable {
    JSONObject toJson();
}
