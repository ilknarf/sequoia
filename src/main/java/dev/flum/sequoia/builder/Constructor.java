package dev.flum.sequoia.builder;

import dev.flum.sequoia.builder.schemas.Page;
import dev.flum.sequoia.builder.schemas.SchemaRegister;
import dev.flum.sequoia.exceptions.InvalidlyShapedDeclarationException;

import org.apache.commons.text.StringEscapeUtils;

import java.io.StringWriter;
import java.util.*;

/**
 * Constructor class that uses the other classes to generate
 * the final HTML result.
 */
public class Constructor {
    String templateLocation;
    Renderer renderer;
    List<Page> pages;

    /**
     * Constructor uses a given template theme location to
     * generate HTML from the parsed YAML
     * @param templateLocation
     */
    public Constructor(String templateLocation) {
        Renderer renderer = new Renderer(templateLocation);

        this.templateLocation = templateLocation;
        this.renderer = renderer;
        this.pages = new ArrayList<>();
    }

    /**
     * construct converts a parsed Yaml portion into a String
     * @param args
     * @return
     * @throws InvalidlyShapedDeclarationException
     */
    public String construct(LinkedHashMap<String, Object> args) throws InvalidlyShapedDeclarationException {
        try {
            if (args.containsKey("type") && (args.get("type").equals("navbar"))) {
                args.put("pages", this.pages);
            }

            for (String key : args.keySet()) {
                Object o = args.get(key);
                if (o instanceof LinkedHashMap) {
                    args.put(key, construct((LinkedHashMap<String, Object>) o));
                }
            }

            StringWriter w = new StringWriter();


            Class<?> c = SchemaRegister.get((String) args.get("type"));

            if (c != null) {
                c.getConstructor(LinkedHashMap.class).newInstance(args);
                renderer.render(w, c.getConstructor(LinkedHashMap.class).newInstance(args));

                return StringEscapeUtils.unescapeHtml4(w.toString());
            } else {
                throw new Exception("invalid type");
            }
        } catch (Exception e) {
            throw new InvalidlyShapedDeclarationException("invalid input: " + e.getLocalizedMessage());
        }
    }

    /**
     * build constructs a LinkedHashMap of generated HTML for the entire site.
     * @param site
     * @return
     * @throws InvalidlyShapedDeclarationException
     */
    public HashMap<String, String> build(LinkedHashMap<String, Object> site)
            throws InvalidlyShapedDeclarationException {

        HashMap<String, String> siteRender = new HashMap<>();

        String url;

        try {
            for (String key : site.keySet()) {


                LinkedHashMap<String, Object> l = (LinkedHashMap<String, Object>) site.get(key);

                l.put("name", key);

                // if no url, generate one based on name.
                if (l.containsKey("url")) {
                    url = (String) l.get("url");
                } else {
                    url = key.toLowerCase().replace(' ', '\0');
                }

                pages.add(new Page(key, url));
            }

            Iterator<Page> p = pages.iterator();

            for (String key : site.keySet()) {
                siteRender.put(p.next().getUrl(), construct((LinkedHashMap<String, Object>) site.get(key)));
            }

            return siteRender;

        } catch (ClassCastException e) {
            throw new InvalidlyShapedDeclarationException(e.getLocalizedMessage());
        }
    }
}
