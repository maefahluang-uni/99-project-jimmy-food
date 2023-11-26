package th.mfu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.Cart;
import th.mfu.domain.Item;
import th.mfu.domain.User;
import th.mfu.repository.CartRepository;
import th.mfu.repository.ItemRepository;
import th.mfu.repository.RestaurantRepository;
import th.mfu.repository.UserRepository;

@Controller
public class FoodController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private ItemRepository itemRepo;
    
    private Long userId;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}


    // OrderItemService.java
    @Service
    public class OrderItemService {
    @Autowired
    private CartRepository orderItemRepository;

    public void saveOrderItem(Cart orderItem) {
        orderItemRepository.save(orderItem);
    }
}

@Service
    public class CartService{

    @Autowired
    private CartRepository cartRepo;

    public void saveCart(Cart cart) {
        cartRepo.save(cart);
    }
}
    //UserService.java
    @Service
    public class UserService{
    @Autowired
    private UserRepository userRepo;

    public List<User> getAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}

    @GetMapping("/game")
    public String showGamePage() {
        return "index"; // This assumes that your HTML file is still named "index.html"
    }

    // to show all shops for the buyer to browse (same for showing discount, popular, and delivery free items)
    @GetMapping("/user-homepage")
    public String listRestaurant(Model model) {
        model.addAttribute("restaurant", restaurantRepo.findAll());
        return "user-homepage";
    }

   // to show items of the selected shop
@GetMapping("/restaurant-items/{id}")
public String showItems(@PathVariable Long id, Model model) {
    Item item = itemRepo.findById(id).orElse(null);
    if (item != null) {
        model.addAttribute("restaurantItems", item);
        return "restaurant-items";
    } else {
        // Handle the case where the item is not found
        return "item-not-found"; // You can create a custom error page or redirect as needed
    }
}



    // to make payment (to move items from cart to order)
/* @GetMapping("/make-order/{id}")
public String makeOrder(@PathVariable Long id, Model model) {
    User user = userRepo.findById(id).orElse(null);
    if (user != null && user.getCart() != null && !user.getCart().isEmpty()) {
        Order order = new Order();
        List<Item> cartItems = (List<Item>) user.getCart();
        List<Cart> orderItems = new ArrayList<>();

            for (Item cartItem : cartItems) {
                Cart orderItem = new Cart();
                orderItem.setItem(cartItem);
                orderItems.add(orderItem);
            }

            order.setOrderItems(orderItems);
            orderRepo.save(order);
            user.setCart((Cart) cartItems);

            userRepo.save(user);
        }

        order.setOrderItems(orderItems);
        orderRepo.save(order);
        userRepo.save(user);

    return "thank-you-page";
}
 */

}