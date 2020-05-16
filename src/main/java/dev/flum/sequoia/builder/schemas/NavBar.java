package dev.flum.sequoia.builder.schemas;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * NavBar is the backing class behind the NavBar template schema
 */
public class NavBar {


    List<Page> pages;

    public NavBar(LinkedHashMap args) throws ClassCastException {
        this.pages = (List<Page>) args.get("pages");
    }

    List<Page> pages() {
        return pages;
    }

}
