package m3.prettyobject.formater;

import m3.prettyobject.formater.wrappers.KeyValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class MapFormat implements Formatter {

    private final Map<Object, Object> map;

    public MapFormat(Object map) {
        this.map = Collections.unmodifiableMap((Map<?, ?>) map);
    }

    @Override
    public boolean isMultiline() {
        return true;
    }

    @Override
    public boolean isIndexed() {
        return true;
    }

    @Override
    public String format() {
        return null;
    }

    @Override
    public String getPreamble() {
        return map.getClass() + " {";
    }

    @Override
    public String getPostamble() {
        return "}";
    }

    @Override
    public Iterable<Object> getChildren() {
        Set<Object> keys = map.keySet();
        int maxKeyLength = 0;
        for (Object key: keys) {
            maxKeyLength = Math.max(maxKeyLength, String.valueOf(key).length());
        }

        ArrayList<Object> keyValues = new ArrayList<Object>();

        for (Map.Entry e: map.entrySet()) {
            keyValues.add(new KeyValue(e.getKey(), maxKeyLength, e.getValue()));
        }

        return keyValues;
    }

    @Override
    public int maxChildrenCount() {
        return map.size();
    }
}
