package my.handel.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "Product")
public class Product extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int Id;

    @Column(name = "NAME")
    public String name;

    @Column(name = "CATEGORY")
    public String category;

    @Column(name = "COUNT")
    public int count;

    @Column(name = "EXPIRATIONDATE")
    public LocalDate expirationDate;

    //region Constructor
    public Product() {
    }

    public Product(String name, String category, int count, LocalDate date) {
        this.name = name;
        this.category = category;
        this.count = count;
        this.expirationDate = date;
    }
//endregion

    @Override
    public String toString() {
        return "Product: "+ name + ", category='" + category +", "+ count +"count "+", expirationDate ";
    }
}
