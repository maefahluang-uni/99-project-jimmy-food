package th.mfu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;

    @ManyToOne
    private Order order;

    // Constructors

    // Default constructor
    public OrderItem() {
    }

    // Parameterized constructor
    public OrderItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Item cartItem) {
        this.id = cartItem;
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

    public void setId(Item cartItem) {
    }
}
