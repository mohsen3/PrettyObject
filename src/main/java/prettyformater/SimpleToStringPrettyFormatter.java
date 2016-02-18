package prettyformater;

public class SimpleToStringPrettyFormatter implements PrettyFormatter {

    protected final Object obj;

    public SimpleToStringPrettyFormatter(Object obj) {
        this.obj = obj;
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
        return String.valueOf(obj);
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
