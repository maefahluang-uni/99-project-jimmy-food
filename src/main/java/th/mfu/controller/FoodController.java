package th.mfu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import th.mfu.domain.Item;
import th.mfu.domain.Order;
import th.mfu.domain.OrderItem;
import th.mfu.domain.Restaurant;
import th.mfu.repository.ItemRepository;
import th.mfu.repository.OrderRepository;
import th.mfu.repository.RestaurantRepository;
import th.mfu.repository.UserRepository;

@Controller
public class FoodController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RestaurantRepository restaurantRepo;

    @Autowired
    ItemRepository itemRepo;

    @Autowired
    OrderRepository orderRepo;

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
    }

    @Transactional
    // to login for each type of user

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
            order.setOrderItems(orderItems);
            orderRepo.save(order);
            ((th.mfu.domain.User) user).setOrder(order);
            user.getCart().clear();
            userRepo.save(user);
        }
        return "thank-you"; // แก้เป็นชื่อ view ที่ต้องการ render หน้า thank-you
    }

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
