import prettyformater.KeyValue;
import prettyformater.PrettyFormatter;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Formatter {
    private final PrettyFormatRegistry registry;
    private final int INDENT = 2;
    private final Set<Object> alreadyFormatted = new HashSet<Object>();

    public Formatter(PrettyFormatRegistry registry) {
        this.registry = registry;
    }

    public void format(Object obj, Writer out) throws IOException {
        alreadyFormatted.clear();
        ArrayList<Object> objectPath = new ArrayList<Object>();
        formatHelper(objectPath, 0, out, obj, true);
    }

    private void formatHelper(ArrayList<Object> objectPath, int indents, Appendable out, Object obj, boolean indentFirstLine) throws IOException {
        if (obj == null) {
            out.append("null\n");
            return;
        }

        if (objectPath.contains(obj)) {
            if (indentFirstLine) {
                out.append(spaces(indents));
            }
            out.append("Circular Reference: ");
            out.append(obj.toString());
            out.append('\n');
            return;
        }

        if (obj instanceof KeyValue) {
            KeyValue keyValue = (KeyValue) obj;
            String fmt = "%" + keyValue.getMaxKeyLength() + "s => ";
            out.append(spaces(indents));
            out.append(String.format(fmt, String.valueOf(keyValue.getKey())));
            formatHelper(objectPath, indents + keyValue.getMaxKeyLength() + 4, out, keyValue.getValue(), false);
        } else {
            PrettyFormatFactory factory = registry.find(obj);
            PrettyFormatter formatter = factory.mkPrettyFormatter(obj);
            if (formatter.isMultiline()) {
                if (indentFirstLine) {
                    out.append(spaces(indents));
                }
                out.append(formatter.getPreamble());
                out.append('\n');

                objectPath.add(obj);

                for (Object child:formatter.getChildren()) {
                    formatHelper(objectPath, indents + INDENT, out, child, true);
                }

                objectPath.remove(obj);

                out.append(spaces(indents));
                out.append(formatter.getPostamble());
                out.append('\n');
            } else {
                if (indentFirstLine) {
                    out.append(spaces(indents));
                }
                out.append(formatter.format());
                out.append('\n');
            }
        }

    }

    private CharSequence spaces(int n) {
        StringBuilder spaces = new StringBuilder();

        for (int i = 0; i < n; i++) {
            spaces.append(' ');
        }

        return spaces;
    }

}
