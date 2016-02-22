package m3.prettyobject.examples;

import m3.prettyobject.PrettyFormat;
import m3.prettyobject.PrettyFormatRegistry;
import m3.prettyobject.ReflectionPrettyFormatFactory;
import m3.prettyobject.formatter.PrimitiveTypeFormatter;

import java.io.IOException;

public class IntHexFormatter extends PrimitiveTypeFormatter {
    public IntHexFormatter(Object obj) {
        super(obj);
    }

    @Override
    public String format() {
        if (obj instanceof Integer) {
            Integer i = (Integer) this.obj;
            return "0x" + Integer.toHexString(i);
        }
        else {
            // This shouldn't happen,
            // unless the formatter is registered for the wrong class.
            return super.format();
        }
    }

    public static void main(String[] args) throws IOException, NoSuchMethodException {
        PrettyFormatRegistry registry = PrettyFormatRegistry.createDefaultInstance();
        registry.register(Integer.class, new ReflectionPrettyFormatFactory(IntHexFormatter.class));
        PrettyFormat formatter = new PrettyFormat(registry);
        formatter.format(new int[]{16, 32, 64}, System.out);
    }
}
