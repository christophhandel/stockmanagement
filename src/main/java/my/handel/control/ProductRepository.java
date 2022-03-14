package my.handel.control;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import my.handel.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product>{

    @Inject
    ProductRepository productRepository;

    public Product getProductByName(String name){
        return productRepository.find("select p from Product p where p.name:=name",name).singleResult();
    }

}
