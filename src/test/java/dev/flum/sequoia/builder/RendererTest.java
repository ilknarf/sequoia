package dev.flum.sequoia.builder;

import dev.flum.sequoia.builder.schemas.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class RendererTest {

    @Test
    public void shouldRenderOneItemProperly() {
        ArrayList<Page> pages = new ArrayList<>();

        LinkedHashMap<String, Object> args = new LinkedHashMap<>();
        args.put("name", "Hello");
        args.put("url", "/hello");

        pages.add(new Page(args));

        Renderer r = new Renderer("src/main/resources/new-site/theme/");

        StringWriter w = new StringWriter();


        LinkedHashMap<String, Object> args2 = new LinkedHashMap<>();
        args2.put("pages", pages);

        try {
        r.render(w, new NavBar(args2));


        String rendered = w.toString();
        w.close();

        File f = new File("src/test/resources/render-test.html");


            BufferedReader br = new BufferedReader(new FileReader(f));

            StringBuilder s = new StringBuilder();

            String first = br.readLine();
            String n;

            while ((n = br.readLine()) != null) {
                s.append(first);
                first = n;
                s.append("\n");
            }

            s.append(first);


            Assertions.assertEquals(rendered, "\n" + s.toString() + "\n", "not equal");
        } catch (Exception e) {
            Assertions.fail("unable to run test" + e);
        }
    }

    @Test
    public void canRenderTextSchema() {
        Renderer r = new Renderer("src/main/resources/new-site/theme");

        StringWriter w = new StringWriter();

        LinkedHashMap<String, Object> m = new LinkedHashMap<>();

        m.put("hello", "maybe");
        m.put("goodbye", "not");



        Assertions.assertDoesNotThrow(() -> r.render(w, new Text(m)));

    }
}
