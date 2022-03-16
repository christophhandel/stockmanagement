package my.handel.control;

import io.quarkus.runtime.StartupEvent;
import my.handel.entity.Product;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

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
        LocalDateTime LMS = LocalDateTime.of(2023, 2, 13,0,0);
        LocalDateTime BS = LocalDateTime.of(2024, 2, 1,0,0);
        LocalDateTime AIS = LocalDateTime.of(2022, 9, 11,0,0);
        productRepository.persist(new Product("Linsen mit Speck","Konserve Dosen",4,LMS.toLocalDate()));
        productRepository.persist(new Product("Bohnen Suppe","Konserve Dosen",4,BS.toLocalDate()));
        productRepository.persist(new Product("Ananas in Scheiben","Konserve Dosen",8,AIS.toLocalDate()));
    }
    // end::init-bean[]
}
