package model;

import persistence.SourceRankerWritable;

import java.util.ArrayList;

import org.json.*;

// A representation of the list of sources to be ranked
public class SourceRanker implements SourceRankerWritable {
    private ArrayList<Source> sourcesList;

    public SourceRanker() {
        sourcesList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new source to a given list and returns true if the title of the source is not already present
    //            within the list, otherwise returns false
    public boolean addSource(Source s) {
        for (Source so : sourcesList) {
            if (s.getTitle() == so.getTitle()) {
                return false;
            }
        }
        sourcesList.add(s);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: removes a source in a given index and returns true if index provided is less the size of the exisiting
    //          list, otherwise return false
    public boolean removeSource(int index) {
        if (index < sourcesList.size()) {
            sourcesList.remove(index);
            return true;
        } else {
            return false;
        }
    }


    // EFFECTS: returns the list of sources
    public ArrayList<Source> getSourceList() {
        return sourcesList;
    }

    // EFFECTS: returns the size of the source list
    public int sourceListSize() {
        return sourcesList.size();
    }

    // MODIFIES: this
    // EFFECTS: if a given parameter matches any substring within the text body of a source, increments the rank
    //          score of the source by 1.
    public void rankingSystemForCourse(String parameter) {
        for (Source s : sourcesList) {
            String text = new String();
            text = s.getBody();
            if (text.contains(parameter)) {
                s.incrRank();
            }
        }
    }

    // EFFECTS: if the given identifier matches any of the identifier for a source in the list,
    //          returns the rank for the source. If no matches are found, return -999.
    public int getIndividualSourceRank(String i) {
        for (Source s : sourcesList) {
            if (s.getIdentifier() == i) {
                return s.getRank();
            }
        }
        return -999;
    }

    // EFFECTS: returns new JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("sources", sourcesToJson());
        return json;
    }

    // EFFECTS: returns sources in source ranker as a JSON array
    private JSONArray sourcesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Source s : sourcesList) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}
