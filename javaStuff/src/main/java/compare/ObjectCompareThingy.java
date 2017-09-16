package compare;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectCompareThingy {
    public static boolean compare(Object o1, Object o2) {
        if (o1 == null || o2 == null) return false;
        try {
            if (o1.getClass() != o2.getClass()) return false;
            Field[] fieldsOfO1 = o1.getClass().getDeclaredFields();
            Map<String, Field> fieldsOfO2Map = fieldsToMap(o2.getClass().getDeclaredFields());
            for (Field field : fieldsOfO1) {
                field.setAccessible(true);
                Object value = field.get(o1);
                if (value == null) return false;

                Field o2Field = fieldsOfO2Map.get(field.getName());
                o2Field.setAccessible(true);
                Object value2 = o2Field.get(o2);
                if (value2 == null) return false;

                if (!value.equals(value2)) {
                    return false;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    private static Map<String, Field> fieldsToMap(Field[] fields) {
        Map<String, Field> result = new HashMap();
        for (int i = 0; i < fields.length; i++) {
            result.put(fields[i].getName(), fields[i]);
        }
        return result;
    }
}
