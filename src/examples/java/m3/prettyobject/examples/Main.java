package m3.prettyobject.examples;

import m3.prettyobject.PrettyFormat;
import m3.prettyobject.PrettyFormatRegistry;

import java.io.IOException;
import java.util.ArrayList;

class Example {
    String str = "string-field";
    Object nil = null;
    int i = 0;
    Object obj;
    Example() {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        arrayList.add("string-list-item");
        arrayList.add(123);
        arrayList.add(new int[]{10, 20, 30});
        this.obj = arrayList;
    }
}


public class Main {
    public static void main(String[] args) throws IOException {
        PrettyFormat formatter = new PrettyFormat(PrettyFormatRegistry.createDefaultInstance());
        formatter.format(new Example(), System.out);
    }
}
