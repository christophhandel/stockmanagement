package my.handel.boundary;

import my.handel.control.ProductRepository;
import my.handel.entity.Product;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Transactional
@Path("product")
public class ProductApi
{

    @Inject
    ProductRepository productRepository;

    @Inject
    ProductResource productResource;

    Logger LOG;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAll(){
        return Response.ok(productRepository.findAll()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response create(
            @Context UriInfo uriInfo
            , @FormParam("name") String name
            , @FormParam("category") String category
            , @FormParam("count") int count
    ) {
        Product product = productRepository.getProductByName(name);

        if (product == null) {
            String message = "Product is mandatory for crating";

            /*return Response
                    .status(Response.Status.OK)
                    .entity(productResource.showError(e.getMessage()))
                    .type(MediaType.TEXT_HTML)
                    .build();

             */
        }

        try {
            Product newProduct = new Product(name, category, count);
            productRepository.persist(newProduct);
        } catch (Exception e) {
            LOG.error("Exception '" + e.getMessage() + "' raised");
            /*return Response
                    .status(Response.Status.OK)
                    .entity(productResource.showError(e.getMessage()))
                    .type(MediaType.TEXT_HTML)
                    .build();

             */
        }
        return Response.status(301)
                .location(URI.create("/"))
                .build();
    }
}
