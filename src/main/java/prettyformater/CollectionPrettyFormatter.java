package prettyformater;

import java.util.Collection;

public class CollectionPrettyFormatter implements PrettyFormatter {

    private final Collection obj;

    public CollectionPrettyFormatter(Object collection) {
        this.obj = (Collection) collection;
    }

    @Override
    public boolean isMultiline() {
        return true;
    }

    @Override
    public boolean isIndexed() {
        return true;
    }

    @Override
    public String format() {
        return null;
    }

    @Override
    public Symbol getPreamble() {
        return new Symbol(obj.getClass().getSimpleName() + " {");
    }

    @Override
    public Symbol getPostamble() {
        return new Symbol("}");
    }

    @Override
    public Iterable<Object> getChildren() {
        return obj;
    }

    @Override
    public int maxChildrenCount() {
        return obj.size();
    }
}
