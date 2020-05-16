package dev.flum.sequoia.builder.schemas;

import java.util.LinkedHashMap;

/**
 * Page is the backing code behind the page.html template.
 * It is the basis for each html page.
 */
public class Page {

    String name;
    String url;

    public Page(LinkedHashMap<String, Object> args) throws ClassCastException {
        this.name = (String) args.get("name");
        this.url = (String) args.get("url");
    }

    public Page(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
