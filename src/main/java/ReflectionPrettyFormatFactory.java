import prettyformater.PrettyFormatter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionPrettyFormatFactory implements PrettyFormatFactory {
    private final Constructor<? extends PrettyFormatter> constructor;

    public ReflectionPrettyFormatFactory(Class<? extends PrettyFormatter> clazz) throws NoSuchMethodException {
        constructor = clazz.getConstructor(Object.class);
    }

    @Override
    public PrettyFormatter mkPrettyFormatter(Object obj) {
        try {
            return constructor.newInstance(obj);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
