package prettyformater;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArrayPrettyFormatter implements PrettyFormatter {

    private final Object arr;

    public ArrayPrettyFormatter(Object arr) {
        if (!arr.getClass().isArray())
            throw new RuntimeException("object not an array");

        this.arr = arr;
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
        return "[";
    }

    @Override
    public String getPostamble() {
        return "]";
    }

    @Override
    public Iterable<Object> getChildren() {
        int length = Array.getLength(arr);
        ArrayList<Object> items = new ArrayList<Object>(length);

        for (int i = 0; i < length; i++) {
            items.add(Array.get(arr, i));
        }

        return items;
    }

    @Override
    public int maxChildrenCount() {
        return Array.getLength(arr);
    }
}
