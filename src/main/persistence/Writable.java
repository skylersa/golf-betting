package persistence;

import org.json.JSONObject;

/*
 * From JSONSerializationDemo
 */
public interface Writable {
    
    // EFFECTS: returns this a JSON object
    JSONObject toJson();
}
