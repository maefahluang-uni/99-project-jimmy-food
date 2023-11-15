package th.mfu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

<<<<<<< HEAD
    @OneToMany(cascade = CascadeType.ALL)
    private Item orderItem;
=======
        @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Cart> orderItems;


    // Constructors

    // Default constructor
    public Order() {
    }

    // Parameterized constructor
    public Order(double amount, List<Cart> orderItems) {
        this.amount = amount;
        this.orderItems = orderItems;
    }

    // Getters and setters
>>>>>>> e3bc913217bd798c993ab41ba535a983a6075e98

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

<<<<<<< HEAD
    public Item getOrderItem() {
        return orderItem;
=======
    public User getUser() {
        return user;
>>>>>>> e3bc913217bd798c993ab41ba535a983a6075e98
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Cart> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Cart> orderItems) {
        this.orderItems = orderItems;
    }
}
