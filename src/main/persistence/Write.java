package persistence;

import org.json.JSONObject;

// EFFECTS: returns this as a JSOBObject
public interface Write {
    JSONObject toJson();
}
