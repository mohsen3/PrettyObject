package m3.prettyobject.formatter;

public class SimpleToStringFormatter implements Formatter {

    protected final Object obj;

    public SimpleToStringFormatter(Object obj) {
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
