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
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Transactional
@Path("api/product")
public class ProductApi
{
    @Inject
    Logger LOG;

    @Inject
    ProductResource productResource;

    @Inject
    ProductRepository productRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response
                .ok(productRepository.findAll().list()).build();
    }

    /**
     * vehicle=2&person=3&from_date=2023-01-01&to_date=2023-01-10
     *
     * Rückgabe einer TemplateInstance innerhalb einer Response
     *  - https://www.programcreek.com/java-api-examples/?api=io.quarkus.qute.TemplateInstance
     *  - Example 2
     *
     * @return redirect auf index.html oder error page
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response create(
            @Context UriInfo uriInfo
            , @FormParam("name") String name
            , @FormParam("category") String category
            , @FormParam("count") int count
            , @FormParam("expirationDate") String date
    ) {
        try {
            LocalDate newDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);

            Product newProduct = new Product(name,category,count,newDate);
            productRepository.persist(newProduct);
        } catch (Exception e) {
            LOG.error("Exception '" + e.getMessage() + "' raised");
        }
        return Response.status(301)
                .location(URI.create("/"))
                .build();
    }
}
