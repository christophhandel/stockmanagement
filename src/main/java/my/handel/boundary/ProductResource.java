package my.handel.boundary;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import my.handel.control.ProductRepository;
import my.handel.entity.Product;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("add")
public class ProductResource {

    @Inject
    ProductRepository productRepository;

    @CheckedTemplate
    public static class Templates {
        //public static native TemplateInstance product(Product product);
        public static native TemplateInstance products(List<Product> products);
        public static native TemplateInstance addProduct(List<Product> products);
        public static native TemplateInstance error(String msg);
    }


/*
    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(@PathParam("id") long id) {
        Product product = productRepository.findById(id);
        return Templates.product(product);
    }

 */


    @GET
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
