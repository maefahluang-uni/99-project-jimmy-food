package th.mfu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Cart> orderItems;

    // Constructors

    // คอนสตรักเตอร์เริ่มต้น
    public Order() {
    }

    // คอนสตรักเตอร์พารามิเตอร์
    public Order(double amount, List<Cart> orderItems) {
        this.amount = amount;
        this.orderItems = orderItems;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Cart> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Cart> orderItems) {
        this.orderItems = orderItems;
    }
}
