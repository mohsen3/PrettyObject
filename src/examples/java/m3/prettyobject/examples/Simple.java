package m3.prettyobject.examples;

import m3.prettyobject.PrettyFormat;
import m3.prettyobject.PrettyFormatRegistry;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Example {
    private String str = "string-field";
    Object nil = null;
    int i = 10;
    List<Object> list;
    Map<Object, Object> map;

    Example() {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        arrayList.add("string-list-item");
        arrayList.add(123);
        arrayList.add(new int[]{10, 20, 30});
        this.list = arrayList;

        HashMap<Object, Object> map = new HashMap<Object, Object>();
        map.put(new Point(3, 4), 5);
        this.map = map;
    }
}

public class Simple {
    public static void main(String[] args) throws IOException {
        PrettyFormat formatter = new PrettyFormat(PrettyFormatRegistry.createDefaultInstance());
        formatter.format(new Example(), System.out);
    }
}
