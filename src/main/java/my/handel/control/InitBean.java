package my.handel.control;

import io.quarkus.runtime.StartupEvent;
import my.handel.entity.Product;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

public class InitBean {

    @Inject
    ProductRepository productRepository;

    void init(@Observes StartupEvent event) {
        if(!"test".equals(io.quarkus.runtime.configuration.ProfileManager.getActiveProfile())) {
            loadData();
        }

    }

    // tag::init-bean[]
    @Transactional
    void loadData() {
        productRepository.persist(new Product("Linsen mit Speck","Konserve Dosen",4));
        productRepository.persist(new Product("Bohnen Suppe","Konserve Dosen",4));
        productRepository.persist(new Product("Ananas in Scheiben","Konserve Dosen",8));
    }
    // end::init-bean[]
}
