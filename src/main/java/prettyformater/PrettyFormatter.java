package prettyformater;

public interface PrettyFormatter {
    boolean isMultiline();
    boolean isIndexed();
    String format();
    String getPreamble();
    String getPostamble();
    Iterable<Object> getChildren();
    int maxChildrenCount();
}
