package dev.flum.sequoia.builder.schemas;

import java.util.LinkedHashMap;

/**
 * Body is the backing template behind the body.html template
 */
public class Body {

    String title;
    String body;

    Body(LinkedHashMap<String, Object> args) throws ClassCastException {
        this.title = (String) args.get("title");
        this.body = (String) args.get("body");
    }
}
