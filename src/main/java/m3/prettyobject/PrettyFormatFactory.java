package m3.prettyobject;

import m3.prettyobject.formatter.Formatter;

public interface PrettyFormatFactory {
    Formatter mkPrettyFormatter(Object obj);
}
