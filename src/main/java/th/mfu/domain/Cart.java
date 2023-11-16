package th.mfu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;

    @ManyToOne
    private Order order;

    // Constructors

    // Default constructor
    public Cart() {
    }

    // Parameterized constructor
    public Cart(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

<<<<<<< HEAD:src/main/java/th/mfu/domain/OrderItem.java
    public void setItem(Item cartItem) {
    }
=======
    public void setItem(Order cartItem) {
        this.order = cartItem;
    }

    public void setItem(Item cartItem) {
    }
    
    
>>>>>>> origin/main:src/main/java/th/mfu/domain/Cart.java
}
