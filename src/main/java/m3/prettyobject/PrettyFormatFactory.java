package m3.prettyobject;

import m3.prettyobject.formater.Formatter;

public interface PrettyFormatFactory {
    Formatter mkPrettyFormatter(Object obj);
}
