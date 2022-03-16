package my.handel.control;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import my.handel.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product>{

}
