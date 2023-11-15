package th.mfu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

<<<<<<< HEAD
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> e3bc913217bd798c993ab41ba535a983a6075e98
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import th.mfu.domain.Cart;
import th.mfu.domain.Item;
import th.mfu.domain.Order;
<<<<<<< HEAD
import th.mfu.domain.OrderItem;
import th.mfu.domain.Restaurant;
=======
import th.mfu.domain.User;
>>>>>>> e3bc913217bd798c993ab41ba535a983a6075e98
import th.mfu.repository.ItemRepository;
import th.mfu.repository.OrderRepository;
import th.mfu.repository.RestaurantRepository;
import th.mfu.repository.UserRepository;

@Controller
public class FoodController {
<<<<<<< HEAD

    @Autowired
    UserRepository userRepo;
=======
>>>>>>> e3bc913217bd798c993ab41ba535a983a6075e98

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private ItemRepository itemRepo;

<<<<<<< HEAD
    // User

    // to create User account
    @GetMapping("/add-buyer")
    public String addABuyerSingupForm(Model model) {
        model.addAttribute("user", new User());
        return "buyer-signup"; // แก้เป็นชื่อ view ที่ต้องการ render หน้า buyer-signup
    }

    // to save User account
    @PostMapping("/save-buyer")
    public String saveUser(@ModelAttribute User user) {
        userRepo.save(user);
        return "redirect:/buyer-homepage"; // แก้เป็น URL ที่ต้องการ redirect
=======
    @Autowired
    private OrderRepository orderRepo;

    private Long userId;

    // User

    // to create User account
    @GetMapping("/add-user-signup")
    public String addBuyerSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "user-signup-form";
    }

    // to save User account
    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute("user") User user) {
        userRepo.save(user);
        return "redirect:/";
>>>>>>> e3bc913217bd798c993ab41ba535a983a6075e98
    }

    @Transactional
    // to login for each type of user

<<<<<<< HEAD
    // to show all shops for buyer to browse
    @GetMapping("/buyer-homepage")
    public String listSeller(Model model) {
        model.addAttribute("sellers", restaurantRepo.findAll());
        return "buyer-homepage"; // แก้เป็นชื่อ view ที่ต้องการ render หน้า buyer-homepage
    }

    // to show items of the selected shop
    @GetMapping("/shop-items/{id}")
    // id is seller id
    public String showItems(@PathVariable Long id, Model model) {
        model.addAttribute("shopitems", itemRepo.findAllById(id));
        return "shop-items"; // แก้เป็นชื่อ view ที่ต้องการ render หน้า shop-items
    }

    // to add items to the cart
    @GetMapping("/user-add-food/{id}")
    // id is item id
    public String addToCart(@ModelAttribute User user, @PathVariable Long id, Model model) {
        Item item = itemRepo.findById(id).orElse(null);
        if (item != null) {
            ((th.mfu.domain.User) user).getCart().addAll(item);
            return "redirect:/buyer-homepage";
        }
        return "error"; // แก้เป็นชื่อ view ที่ต้องการ render หน้า error
    }

    // to view cart
    @GetMapping("/view-cart/{id}")
    public String viewCart(@PathVariable Long id, Model model) {
        User cartUser = (User) userRepo.findById(id).orElse(null);
        if (cartUser != null && ((th.mfu.domain.User) cartUser).getCart() != null) {
            model.addAttribute("cartItems", ((th.mfu.domain.User) cartUser).getCart());
        }
        return "view-cart"; // แก้เป็นชื่อ view ที่ต้องการ render หน้า view-cart
    }

    // to make payment (to move items from cart to order)
    @GetMapping("/make-order/{id}")
    public String makeOrder(@ModelAttribute User user, @PathVariable Long id, Model model) {
        Order order = new Order();
        List<Item> cartItems = user.getCart();
        if (!cartItems.isEmpty()) {
            List<OrderItem> orderItems = new ArrayList<>();
            for (Item cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setItem(cartItem);
                orderItems.add(orderItem);
            }
=======
    // to show all shops for the buyer to browse (same for showing discount, popular, and delivery free items)
    @GetMapping("/user-homepage")
    public String listRestaurant(Model model) {
        model.addAttribute("restaurant", restaurantRepo.findAll());
        return "user-homepage";
    }

    // to show items of the selected shop
    @GetMapping("/restautant-items/{id}")
    public String showItems(@PathVariable Long id, Model model) {
        model.addAttribute("restaurantItems", itemRepo.findAllById(id));
        return "restaurant-items";
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

<<<<<<< HEAD
            for (Item cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setItem(cartItem);
                orderItems.add(orderItem);
            }

>>>>>>> e3bc913217bd798c993ab41ba535a983a6075e98
            order.setOrderItems(orderItems);
            orderRepo.save(order);
            user.setOrders(order);
            user.getCart().clear();
            userRepo.save(user);
=======
        for (Item cartItem : cartItems) {
            Cart orderItem = new Cart();
            orderItem.setItem(cartItem);
            orderItems.add(orderItem); // Fix: Add to orderItems, not cartItems
>>>>>>> origin/main
        }
        return "thank-you"; // แก้เป็นชื่อ view ที่ต้องการ render หน้า thank-you
    }

<<<<<<< HEAD
    //////////////////////////////Seller

    // to create seller account
    @GetMapping("/add-seller")
    public String addASellerSingupForm(Model model) {
        model.addAttribute("seller", new Seller());
        return "seller-signup"; // แก้เป็นชื่อ view ที่ต้องการ render หน้า seller-signup
    }

    // to save seller account
    @PostMapping("/save-seller")
    public String saveSeller(@ModelAttribute Buyer buyer) {
        CrudRepository<Item, Long> buyerRepo;
        buyerRepo.saveAll(buyer);
        return "redirect:/seller-homepage"; // แก้เป็น URL ที่ต้องการ redirect
    }

    // to view the seller's shop menu
    @GetMapping("/view-shop-menu/{id}")
    public String viewShopMenu(@PathVariable Long id, Model model) {
        model.addAttribute("shopItems", itemRepo.findAllById(id));
        return "view-shop-menu"; // แก้เป็นชื่อ view ที่ต้องการ render หน้า view-shop-menu
    }

    // to create new item
    @GetMapping("/add-item")
    public String addItemCreateForm(Model model) {
        model.addAttribute("item", new Item());
        return "add-item"; // แก้เป็นชื่อ view ที่ต้องการ render หน้า add-item
    }

    // to save new item
    @PostMapping("/save-item/{id}")
    // id is seller id
    public String saveItem(@ModelAttribute Item item, @PathVariable Long id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
        if (restaurant != null) {
            restaurant.setItem(item);
            itemRepo.save(item);
        }
        return "redirect:/view-shop-menu/" + id; // แก้เป็น URL ที่ต้องการ redirect
    }

    // to delete item from shop menu
    @GetMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemRepo.deleteById(id);
        return "redirect:/view-shop-menu"; // แก้เป็น URL ที่ต้องการ redirect
    }

    // to show orders
    @GetMapping("/show-orders/{id}")
    public String showOrders(@PathVariable Long id, Model model) {
        model.addAttribute("orders", orderRepo.findAllById(id));
        return "show-orders"; // แก้เป็นชื่อ view ที่ต้องการ render หน้า show-orders
    }
}

    //
=======
        order.setOrderItems(orderItems);
        orderRepo.save(order);
        user.setOrder(order);
        user.getCart().clear();
        userRepo.save(user);
    }
    return "thank-you-page";
}


}
>>>>>>> e3bc913217bd798c993ab41ba535a983a6075e98
