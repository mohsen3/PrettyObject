package prettyformater;

public class CharSequencePrettyFormatter extends SimpleToStringPrettyFormatter {
    public CharSequencePrettyFormatter(Object obj) {
        super(obj);
    }

    @Override
    public String format() {
        if (obj == null)
            return "null";
        else
            return String.format("%s\"%s\"",
                    obj instanceof String ? "" : obj.getClass().getSimpleName() + ": ",
                    obj);
    }
}
