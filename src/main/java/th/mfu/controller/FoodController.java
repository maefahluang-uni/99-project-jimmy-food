package th.mfu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import th.mfu.domain.Cart;
import th.mfu.domain.Item;
import th.mfu.domain.Order;
import th.mfu.domain.User;
import th.mfu.repository.CartRepository;
import th.mfu.repository.ItemRepository;
import th.mfu.repository.OrderRepository;
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

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CartRepository cartRepo;

    private Long userId;

    // User

    // to create User account
    @GetMapping("/add-buyer-signup")
    public String addBuyerSignupForm(Model model) {
        model.addAttribute("buyer", new User());
        return "buyer-signup-form";
    }

    // to save User account
    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute("buyer") User user) {
        userRepo.save(user);
        return "redirect:/";
    }

    @Transactional
    // to login for each type of user

    // to show all shops for the buyer to browse (same for showing discount, popular, and delivery free items)
    @GetMapping("/user-homepage")
    public String listSellers(Model model) {
        model.addAttribute("restaurant", restaurantRepo.findAll());
        return "user-homepage";
    }

    // to show items of the selected shop
    @GetMapping("/show-items/{id}")
    public String showItems(@PathVariable Long id, Model model) {
        model.addAttribute("shopitems", itemRepo.findAllById(id));
        return "shop-items";
    }

   // to add items to the cart
@GetMapping("/user-add-food/{id}")
public String addToCart(@PathVariable Long id, Model model) {
    model.addAttribute("cartItem", new Item());
    Item item = itemRepo.findById(id).orElse(null);
    if (item != null) {
        // Fetch user from session or database
        User user = userRepo.findById(userId).orElse(null); // Replace userId with the actual ID or parameter
        if (user != null) {
            user.getCart().add(item);
            userRepo.save(user);
        }
    }
    return "redirect:/";
}



    // to view cart
    @GetMapping("/view-cart/{id}")
    public String viewCart(@PathVariable Long id, Model model) {
        User cartUser = userRepo.findById(id).orElse(null);
        if (cartUser != null && cartUser.getCart() != null) {
            model.addAttribute("cartItems", cartUser.getCart());
        }
        return "view-cart";
    }

    // to make payment (to move items from cart to order)
@GetMapping("/make-order/{id}")
public String makeOrder(@PathVariable Long id, Model model) {
    User user = userRepo.findById(id).orElse(null);
    if (user != null && user.getCart() != null && !user.getCart().isEmpty()) {
        Order order = new Order();
        List<Item> cartItems = user.getCart();
        List<Cart> orderItems = new ArrayList<>();

        for (Item cartItem : cartItems) {
            Cart orderItem = new Cart();
            orderItem.setItem(cartItem);
            orderItems.add(orderItem); // Fix: Add to orderItems, not cartItems
        }

        order.setOrderItems(orderItems);
        orderRepo.save(order);
        user.setOrder(order);
        user.getCart().clear();
        userRepo.save(user);
    }
    return "thank-you-page";
}


}
