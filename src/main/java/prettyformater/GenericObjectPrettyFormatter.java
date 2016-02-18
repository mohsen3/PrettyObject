package prettyformater;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class GenericObjectPrettyFormatter implements PrettyFormatter {
    private final Object obj;

    public GenericObjectPrettyFormatter(Object obj) {
        this.obj = obj;
    }

    private boolean isNull() {
        return obj == null;
    }

    @Override
    public boolean isMultiline() {
        return !isNull();
    }

    @Override
    public boolean isIndexed() {
        return false;
    }

    @Override
    public String format() {
        return isNull() ? "null" : null;
    }

    @Override
    public String getPreamble() {
        return isNull() ? null : (obj.getClass().getName() + " {");
    }

    @Override
    public String getPostamble() {
        return isNull() ? null : "}";
    }

    @Override
    public Iterable<Object> getChildren() {
        return isNull() ? null : getFieldsMap();
    }

    @Override
    public int maxChildrenCount() {
        return isNull() ? 0 : obj.getClass().getFields().length;
    }

    public Iterable<Object> getFieldsMap() {
        ArrayList<Field> allFields = new ArrayList<Field>();
        getAllFields(allFields, obj.getClass());

        int maxKeyLength = 0;
        for (Field field:allFields) {
            maxKeyLength = Math.max(maxKeyLength, field.getName().length());
        }

        ArrayList<Object> fieldList = new ArrayList<Object>();

        for (Field field:allFields) {
            try {
                field.setAccessible(true);
                fieldList.add(new KeyValue(field.getName(), maxKeyLength, field.get(obj)));
            } catch (IllegalAccessException e) {
                // this should never happen
                e.printStackTrace();
            }
        }

        return fieldList;
    }

    private void getAllFields(ArrayList<Field> allFields, Class<?> clazz) {
        if (clazz == null)
            return;

        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            if (!field.isSynthetic() && !Modifier.isStatic(field.getModifiers()))
            allFields.add(field);
        }
        getAllFields(allFields, clazz.getSuperclass());
    }
}
