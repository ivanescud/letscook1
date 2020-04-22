package com.simplelifestudio.letscook1.model;

import java.io.Serializable;
import java.util.Map;

public class MapData implements Serializable {

    private Map<String, Boolean> data;

    public MapData() {
    }

    public MapData(Map<String, Boolean> data) {
        this.data = data;
    }


    public Map<String, Boolean> getData() {
        return data;
    }

    public void setData(Map<String, Boolean> data) {
        this.data = data;
    }
}
