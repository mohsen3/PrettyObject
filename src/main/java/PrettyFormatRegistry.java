import prettyformater.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PrettyFormatRegistry {
    public static PrettyFormatRegistry defaultInstance = mkDefaultInstance();

    Map<Class<?>, PrettyFormatFactory> registry = new HashMap<Class<?>, PrettyFormatFactory>();

    private static PrettyFormatRegistry mkDefaultInstance() {
        PrettyFormatRegistry def = new PrettyFormatRegistry();

        try {
            def.register(Object.class, new ReflectionPrettyFormatFactory(GenericObjectPrettyFormatter.class));

            Class<?> primitiveClasses[] = {
                    Byte.TYPE,
                    Byte.class,
                    Short.TYPE,
                    Short.class,
                    Integer.TYPE,
                    Integer.class,
                    Long.TYPE,
                    Long.class,
                    Float.TYPE,
                    Float.class,
                    Double.TYPE,
                    Double.class,
                    Boolean.TYPE,
                    Boolean.class,
                    Character.TYPE,
                    Character.class,
                    String.class
            };

            for (Class primitiveClass:primitiveClasses) {
                def.register(primitiveClass, new ReflectionPrettyFormatFactory(PrimitiveTypePrettyFormatter.class));
            }

            def.register(Collection.class, new ReflectionPrettyFormatFactory(CollectionPrettyFormatter.class));
            def.register(Map.class, new ReflectionPrettyFormatFactory(MapPrettyFormat.class));
            def.register(KeyValue.class, new ReflectionPrettyFormatFactory(KeyValuePrettyFormatter.class));
            def.register(Enum.class, new ReflectionPrettyFormatFactory(EnumPrettyformatter.class));
            def.register(CharSequence.class, new ReflectionPrettyFormatFactory(CharSequencePrettyFormatter.class));
        } catch (NoSuchMethodException e) {
            // This should never happen
            e.printStackTrace();
        }

        return def;
    }

    public void register(Class<?> clazz, PrettyFormatFactory factory) {
        registry.put(clazz, factory);
    }

    public PrettyFormatFactory find(Object obj) {
        if (obj == null)
            return null;

        if (obj.getClass().isArray()) {
            try {
                return new ReflectionPrettyFormatFactory(ArrayPrettyFormatter.class);
            } catch (NoSuchMethodException e) {
                // This shouldn't happen
                throw new RuntimeException(e);
            }
        }

        Class<?> clazz = obj.getClass();
        return findByClass(clazz);
    }

    public PrettyFormatFactory findByClass(Class<?> clazz) {
        if (registry.containsKey(clazz))
            return registry.get(clazz);
        else {

            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> intr:interfaces) {
                if (registry.containsKey(intr))
                    return registry.get(intr);
            }

            Class<?> superclass = clazz.getSuperclass();
            return superclass == null ? null : findByClass(superclass);
        }
    }

}
