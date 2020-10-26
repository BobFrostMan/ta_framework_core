package com.foggger.domain.ui.component.table.base;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TableRowItem {

    private Map<String, String> data;

    public TableRowItem() {
        data = new LinkedHashMap<>();
    }

    public void addData(String header, String value) {
        data.put(header, value);
    }

    public String getDataByHeader(String header) {
        return data.get(header);
    }

    public int getCellIndexByHeader(String header) {
        Iterator iterator = data.entrySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (entry.getKey().equals(header)) {
                return index;
            }
            index++;
        }
        return index;
    }

    public int getCellIndexByValue(String value) {
        Iterator iterator = data.entrySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (entry.getValue().equals(value)) {
                return index;
            }
            index++;
        }
        return index;
    }

}
