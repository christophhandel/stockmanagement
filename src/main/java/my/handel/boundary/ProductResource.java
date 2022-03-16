package my.handel.boundary;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import my.handel.control.ProductRepository;
import my.handel.entity.Product;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("")
public class ProductResource {

    @Inject
    ProductRepository productRepository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance products(List<Product> products);
        public static native TemplateInstance addProduct(List<Product> products);
        public static native TemplateInstance error(String msg);
    }


    @GET
    @Path("all")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance allProducts() {
        return Templates.products(productRepository.findAll().list());
    }

    @GET
    @Path("add")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance addProduct() {
        return Templates.addProduct(
                productRepository.listAll());
    }

    @GET
    @Path("error")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showError(String message) {
        return Templates.error(message);
    }
}
