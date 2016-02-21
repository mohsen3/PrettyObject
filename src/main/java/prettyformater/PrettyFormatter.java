package prettyformater;

public interface PrettyFormatter {
    boolean isMultiline();
    boolean isIndexed();
    String format();
    Object getPreamble();
    Object getPostamble();
    Iterable<Object> getChildren();
    int maxChildrenCount();
}
