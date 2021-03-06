/*
 * Copyright 2013 - Jeandeson O. Merelis
 */
package tef.dial.response;

import tef.dial.TefDial;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Jeandeson O. Merelis
 */
public class StatusResponse {

    protected TefDial.Operation operation;
    protected Integer identifier;
    protected Map<String, String> fields;

    public TefDial.Operation getOperation() {
        return operation;
    }

    public int getIdentifier() {
        return identifier;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    protected void addField(String key, String value) {
        if (fields == null) {
            fields = new LinkedHashMap<>();
        }
        fields.put(key, value);
    }

    @Override
    public String toString() {
        return "StatusResponse{" + "operation=" + operation + ", identifier=" + identifier + ", fields=" + fields + '}';
    }

}
