package model;

import org.json.JSONObject;
import persistence.SourceRankerWritable;

// A representation of a source with a given identifier, title, text body and rank score
public class Source implements SourceRankerWritable {
    private String ident;
    private String title;
    private String body;
    private int rank;

    // EFFECTS: a source has an identifier, title, body and rank score
    public Source(String ident, String title, String body, int rank) {
        this.ident = ident;
        this.title = title;
        this.body = body;
        this.rank = rank;
    }

    // EFFECTS: return identifier of the source
    public String getIdentifier() {
        return ident;
    }

    // EFFECTS: returns title of the source
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns text body of the source
    public String getBody() {
        return body;
    }

    // MODIFIES: this
    // EFFECTS: increments the rank score by one and returns the new rank score
    public int incrRank() {
        rank = rank + 1;
        return rank;
    }

    // EFFECTS: returns rank score of the source
    public int getRank() {
        return rank;
    }

    // EFFECTS: returns new JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ident", ident);
        json.put("title", title);
        json.put("body", body);
        json.put("rank", rank);
        return json;
    }
}
