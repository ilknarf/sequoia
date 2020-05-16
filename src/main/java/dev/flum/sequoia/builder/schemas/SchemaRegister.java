package dev.flum.sequoia.builder.schemas;

import java.util.HashMap;

/**
 * SchemaRegister provides a set of classes that can
 * be used to construct reference objects.
 */
public class SchemaRegister {
    static HashMap<String, Class<?>> schemas = new HashMap<>();

    static {
        schemas.put("navbar", NavBar.class);
        schemas.put("body", Body.class);
        schemas.put("text", Text.class);
        schemas.put("base", Base.class);
    }

    public static Class<?> get(String name) {
        if (schemas.containsKey(name)) {
            return schemas.get(name);
        }

        // theme type
        return Text.class;
    }
}
