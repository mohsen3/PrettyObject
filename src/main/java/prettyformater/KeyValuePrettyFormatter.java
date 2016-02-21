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
        return " => ";
    }

    @Override
    public Object getPreamble() {
        return keyValue.getKey();
    }

    @Override
    public Object getPostamble() {
        Object value = keyValue.getValue();
        return value == null ? new Symbol(null) : value;
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
