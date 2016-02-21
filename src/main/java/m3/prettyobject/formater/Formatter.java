package m3.prettyobject.formater;

public interface Formatter {
    boolean isMultiline();
    boolean isIndexed();
    String format();
    Object getPreamble();
    Object getPostamble();
    Iterable<Object> getChildren();
    int maxChildrenCount();
}
