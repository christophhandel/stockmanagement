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
    private int Id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "COUNT")
    private int count;

    //@Column(name = "EXPIRATIONDATE")
    //private Date expirationDate;

    //region Constructor
    public Product() {
    }

    public Product(String name, String category, int count) {
        this.name = name;
        this.category = category;
        this.count = count;
    }
//endregion

    @Override
    public String toString() {
        return "Product: "+ name + ", category='" + category +", "+ count +"count "+", expirationDate ";
    }
    /*
     <label for="expirationDate">Expiry Date:</label><br>
    <input type="date" id="expirationDate" name="expirationDate"><br><br>
     */
}
