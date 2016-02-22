package m3.prettyobject;

import m3.prettyobject.formatter.*;
import m3.prettyobject.formatter.wrappers.KeyValue;
import m3.prettyobject.formatter.wrappers.Symbol;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PrettyFormatRegistry {
    Map<Class<?>, PrettyFormatFactory> registry = new HashMap<Class<?>, PrettyFormatFactory>();

    public static PrettyFormatRegistry createDefaultInstance() {
        PrettyFormatRegistry def = new PrettyFormatRegistry();

        try {
            def.register(Object.class, new ReflectionPrettyFormatFactory(GenericObjectFormatter.class));

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
                    Character.class
            };

            for (Class primitiveClass:primitiveClasses) {
                def.register(primitiveClass, new ReflectionPrettyFormatFactory(PrimitiveTypeFormatter.class));
            }

            def.register(Collection.class, new ReflectionPrettyFormatFactory(CollectionFormatter.class));
            def.register(Map.class, new ReflectionPrettyFormatFactory(MapFormatter.class));
            def.register(KeyValue.class, new ReflectionPrettyFormatFactory(KeyValueFormatter.class));
            def.register(Enum.class, new ReflectionPrettyFormatFactory(EnumFormatter.class));
            def.register(CharSequence.class, new ReflectionPrettyFormatFactory(CharSequenceFormatter.class));
            def.register(Symbol.class, new ReflectionPrettyFormatFactory(SymbolFormatter.class));
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
                return new ReflectionPrettyFormatFactory(ArrayFormatter.class);
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
