package dev.flum.sequoia.builder;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.Mustache;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;

/**
 * Renderer wraps a Factory class producing compiled
 * Mustache templates.
 */
public class Renderer {

    protected MustacheFactory mf;
    protected String templateLocation;

    public Renderer(String templateLocation) {
        this.templateLocation = templateLocation;
        mf = new DefaultMustacheFactory();
    }

    /**
     * Render writes to a writer the output of a processed
     * template.
     * @param w
     * @param templateCode
     */
    public void render(Writer w, Object templateCode) throws IOException {

        String template;

        switch(templateCode.getClass().getSimpleName()) {
            case "NavBar":
                template = Paths.get(templateLocation, "navbar.html").toString();
                break;
            case "Body":
                template = Paths.get(templateLocation, "body.html").toString();
                break;
            case "Base":
                template = Paths.get(templateLocation, "base.html").toString();
                break;
            case "Text":
                template = Paths.get(templateLocation, "text.html").toString();
                break;

            default:
                throw new IOException("invalid schema: " + templateCode.getClass());
        }

        Mustache m = mf.compile(template);

        w.write('\n');
        m.execute(w, templateCode).flush();
        w.write('\n');
    }
}
