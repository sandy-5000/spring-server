package com.darkube.server.types;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DynamicObject {
    
    private Map<String, Object> dynamicObject;

    public DynamicObject() {
        dynamicObject = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public void put(String key, Object value) throws Exception {
        String[] path = key.split("[.]");
        String last = path[path.length - 1];
        path = Arrays.copyOf(path, path.length - 1);
        Map<String, Object> object = this.dynamicObject;
        for (String item : path) {
            if (object.get(item) == null) {
                object.put(item, new HashMap<String, Object>());
            }
            try {
                object = (HashMap<String, Object>)object.get(item);
            } catch(Exception e) {
                throw new Exception("Error in inserting");
            }
        }
        object.put(last, value);
    }

    @SuppressWarnings("unchecked")
    public Object get(String key) {
        String[] path = key.split("[.]");
        String last = path[path.length - 1];
        path = Arrays.copyOf(path, path.length - 1);
        Map<String, Object> object = this.dynamicObject;
        for (String item : path) {
            if (object.get(item) == null) {
                object.put(item, new HashMap<String, Object>());
            }
            try {
                object = (HashMap<String, Object>) object.get(item);
            } catch (Exception e) {
                return null;
            }
        }
        return object.get(last);
    }

    public Map<String, Object> map() {
        return dynamicObject;
    }

}
