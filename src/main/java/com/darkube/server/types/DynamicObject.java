package com.darkube.server.types;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.darkube.server.models.User;

public class DynamicObject {

    private HashMap<String, Object> dynamicObject;

    public DynamicObject() {
        dynamicObject = new HashMap<>();
    }

    public boolean isValidType(Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof HashMap<?, ?>) {
            HashMap<?, ?> map = (HashMap<?, ?>) value;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (!(entry.getKey() instanceof String)) {
                    return false;
                }
                if (!isValidType(entry.getValue())) {
                    return false;
                }
            }
            return true;
        }
        if (value.getClass().isArray()) {
            Object[] array = (Object[]) value;
            for (Object object : array) {
                if (!isValidType(object)) {
                    return false;
                }
            }
            return true;
        }
        return value == null
                || value instanceof Integer
                || value instanceof Float
                || value instanceof Double
                || value instanceof String
                || value instanceof User;
    }

    @SuppressWarnings("unchecked")
    public void put(String key, Object value) throws Exception {
        if (key == null) {
            throw new Exception("`key` can't be of null");
        }
        if (!this.isValidType(value)) {
            throw new Exception("value is not a valid type");
        }
        String[] path = key.split("[.]");
        String last = path[path.length - 1];
        path = Arrays.copyOf(path, path.length - 1);
        try {
            HashMap<String, Object> object = this.dynamicObject;
            for (String item : path) {
                if (object.get(item) == null) {
                    object.put(item, new HashMap<String, Object>());
                }
                object = (HashMap<String, Object>) object.get(item);
            }
            object.put(last, value);
        } catch (Exception e) {
            throw new Exception("Error in inserting: " + key);
        }
    }

    @SuppressWarnings("unchecked")
    public Object get(String key) throws Exception {
        if (key == null) {
            throw new Exception("`key` can't be of null");
        }
        String[] path = key.split("[.]");
        String last = path[path.length - 1];
        path = Arrays.copyOf(path, path.length - 1);
        try {
            HashMap<String, Object> object = this.dynamicObject;
            for (String item : path) {
                if (object.get(item) == null) {
                    object.put(item, new HashMap<String, Object>());
                }
                object = (HashMap<String, Object>) object.get(item);
            }
            return object.get(last);
        } catch (Exception e) {
            return null;
        }
    }

    public HashMap<String, Object> map() {
        return dynamicObject;
    }

}
