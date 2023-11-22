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
import javax.persistence.Table;

@Entity
@Table (name= "OrderEntity")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "user_id")
=======
    @ManyToOne(targetEntity = User.class) // Specify the target entity
    @JoinColumn(name = "user_id", referencedColumnName = "id")
>>>>>>> refs/remotes/origin/main
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


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

    public User getUser() {
        return user;
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

    public void setItem(Item cartItem) {
    }
}
