package th.mfu.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private int total_price;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    // Constructors

    // Default constructor
    public Cart() {
    }

    // Parameterized constructor
    public Cart( int quantity, int total_price) {
        this.quantity = quantity;
        this.total_price = total_price;
    }

    // Getters and setters

    public int getTotalPrice() {
        return total_price;
    }

    public void setTotalPrice(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setItem(Item cartItem) {

    }

    public void add(Item item) {
    }

    public boolean isEmpty() {
        return false;
    }

    // Method to calculate the total price based on items in the cart
    public int calculateTotalPrice() {
        int total_price = 0;
        for (Item item : items) {
            total_price += item.getPrice();
        }
        return total_price;
    }
}
