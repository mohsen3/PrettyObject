package prettyformater;

public class KeyValuePrettyFormatter implements PrettyFormatter {

    final private KeyValue keyValue;

    public KeyValuePrettyFormatter(Object keyValue) {
        this.keyValue = (KeyValue) keyValue;
    }

    @Override
    public boolean isMultiline() {
        return false;
    }

    @Override
    public boolean isIndexed() {
        return false;
    }

    @Override
    public String format() {
        return keyValue.toString();
    }

    @Override
    public String getPreamble() {
        return null;
    }

    @Override
    public String getPostamble() {
        return null;
    }

    @Override
    public Iterable<Object> getChildren() {
        return null;
    }

    @Override
    public int maxChildrenCount() {
        return 0;
    }
}
