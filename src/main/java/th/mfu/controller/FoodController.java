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

import th.mfu.domain.Item;
import th.mfu.domain.Order;
import th.mfu.domain.OrderItem;
import th.mfu.domain.User;
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

    // Buyer

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
    @GetMapping("/buyer-homepage")
    public String listSellers(Model model) {
        model.addAttribute("sellers", restaurantRepo.findAll());
        return "buyer-homepage";
    }

    // to show items of the selected shop
    @GetMapping("/show-items/{id}")
    public String showItems(@PathVariable Long id, Model model) {
        model.addAttribute("shopitems", itemRepo.findAllBySellerId(id));
        return "shop-items";
    }

    // to add items to the cart
    @GetMapping("/user-add-food/{id}")
    public String addToCart(@PathVariable Long id, Model model) {
        model.addAttribute("cartItem", new Item());
        Item item = itemRepo.findById(id).orElse(null);
        if (item != null) {
            User user = /* Fetch user from session or database */;
            user.getCart().addAll(item);
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
            List<OrderItem> orderItems = new ArrayList<>();

            for (Item cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setItem(cartItem);
                orderItems.add(orderItem);
            }

            order.setOrderItems(orderItems);
            orderRepo.save(order);
            user.setOrders(order);
            user.getCart().clear();
            userRepo.save(user);
        }
        return "thank-you-page";
    }

    // Seller

    // to create a seller account
    @GetMapping("/add-seller-signup")
    public String addSellerSignupForm(Model model) {
        model.addAttribute("seller", new User()); // Assuming User can be both buyer and seller
        return "seller-signup-form";
    }

    // to save the seller account
    @PostMapping("/save-seller")
    public String saveSeller(@ModelAttribute("seller") User seller) {
        sellerRepo.save(seller);
        return "redirect:/";
    }

    // to view the seller's shop menu
    @GetMapping("/view-shop-menu/{id}")
    public String viewShopMenu(@PathVariable Long id, Model model) {
        model.addAttribute("shopItems", itemRepo.findAllBySellerId(id));
        return "view-shop-menu";
    }

    // to create a new item
    @GetMapping("/add-item")
    public String addItemCreateForm(Model model) {
        model.addAttribute("item", new Item());
        return "add-item-form";
    }

    // to save a new item
    @PostMapping("/save-item/{id}")
    public String saveItem(@ModelAttribute Item item, @PathVariable Long id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
        if (restaurant != null) {
            restaurant.addItem(item);
            itemRepo.save(item);
        }
        return "redirect:/";
    }

    // to delete an item from the shop menu
    @GetMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemRepo.deleteById(id);
        return "redirect:/";
    }

    // to show orders
    @GetMapping("/show-orders/{id}")
    public String showOrders(@PathVariable Long id, Model model) {
        model.addAttribute("orders", orderRepo.findAllBySellerId(id));
        return "show-orders";
    }

    // Accepting orders is enough with just HTML

    // to deny a customer's order
    @GetMapping("/deny-order/{id}")
    public String denyOrder(@PathVariable Long id) {
        orderRepo.deleteById(id);
        return "redirect:/";
    }
}
