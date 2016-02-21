package m3.prettyobject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PrettyFormat {
    private final PrettyFormatRegistry registry;
    private final int INDENT = 2;
    private final Set<Object> alreadyFormatted = new HashSet<Object>();

    public PrettyFormat(PrettyFormatRegistry registry) {
        this.registry = registry;
    }

    public void format(Object obj, Appendable out) throws IOException {
        alreadyFormatted.clear();
        ArrayList<Object> objectPath = new ArrayList<Object>();
        formatHelper(objectPath, 0, out, obj, true);
    }

    private void formatHelper(ArrayList<Object> objectPath, int indents, Appendable out, Object obj, boolean indentFirstLine) throws IOException {
        if (indentFirstLine) {
            out.append(spaces(indents));
        }
        if (obj == null) {
            out.append("null");
            return;
        }

        if (objectPath.contains(obj)) {
            out.append("Circular Reference: ");
            out.append(obj.toString());
            return;
        }

        PrettyFormatFactory factory = registry.find(obj);
        m3.prettyobject.formatter.Formatter formatter = factory.mkPrettyFormatter(obj);

        objectPath.add(obj);
        Object preamble = formatter.getPreamble();
        if (preamble != null) {
            formatHelper(objectPath, 0, out, preamble, true);
        }

        if (formatter.isMultiline()) {
            Iterator<Object> iterator = formatter.getChildren().iterator();
            while (iterator.hasNext()) {
                Object child = iterator.next();
                out.append('\n');
                formatHelper(objectPath, indents + INDENT, out, child, true);
                if (iterator.hasNext()) {
                    out.append(',');
                }
            }
            out.append('\n');
            out.append(spaces(indents));
        } else {
            out.append(formatter.format());
        }

        Object postamble = formatter.getPostamble();
        if (postamble != null) {
            formatHelper(objectPath, indents, out, postamble, false);
        }
        objectPath.remove(obj);
    }

    private CharSequence spaces(int n) {
        StringBuilder spaces = new StringBuilder();

        for (int i = 0; i < n; i++) {
            spaces.append(' ');
        }

        return spaces;
    }

}
