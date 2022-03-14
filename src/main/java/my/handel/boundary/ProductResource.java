package my.handel.boundary;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RequestScoped
public class ProductResource {

/*
    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance showError(String msg);
    }

    @GET
    @Path("error")
    public TemplateInstance showError(String message) {
        return Templates.showError(message);
    }

 */
}
