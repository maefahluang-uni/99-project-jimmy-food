package th.mfu.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.apache.catalina.User;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import th.mfu.domain.Item;
import th.mfu.domain.Order;
import th.mfu.domain.OrderItem;
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


    //User

    //to create User account
    @GetMapping
    public String addABuyerSingupForm(Model model){
        model.addAttribute("User", new User());
        return "";
    }

    //to save User account
    @PostMapping
    public String saveUser(@ModelAttribute User user){
        userRepo.save(user);
        return"";
    }

    @Transactional
    //to login for each type of user
  

    //to show all shops for buyer to browse (same for showing discount, popular and delivery free items)
    @GetMapping("/buyer-homepage")
    public String listSeller (Model model) {
        model.addAttribute("sellers", restaurantRepo.findAll());
        return "";
    }

    //Check line 151
    //to show items of the selected shop
    @GetMapping("")
    //id is seller id
    public String showItems (@PathVariable Long id, Model model) {
        model.addAttribute("shopitems", itemRepo.findAllById(id));
        return "";
    }

    //to add items to the cart
   @GetMapping("/user-add-food/{id}") 
   //id is item id
   public String addToCart (@ModelAttribute User user, @PathVariable Long id,Model model) {
        model.addAttribute("cartItem", new Item());
        //how to get the id of the item the user clicked??
        Item item = itemRepo.findById(id).get();
        ((th.mfu.domain.User) user).getCart().addAll(item);
         ((th.mfu.domain.User) user).getCart().addAll(item);
        return ""; //redirect sthhhh
   }

   //to view cart
   @GetMapping("")
   public String viewCart(@PathVariable Long id, Model model) {
        //find the user by the user id
        User cartUser = (User) userRepo.findById(id).get();
        //loop through the cart of that user
        if((((th.mfu.domain.User) cartUser)).getCart() !=null){
            model.addAttribute("cartItems", ((th.mfu.domain.User) cartUser).getCart());
        }
        return "";
   }

   //********************************************* */
   //to make payment(to move items from cart to order)
   @GetMapping("")
   public String makeOrder(@ModelAttribute User user, @PathVariable Long id, Model model) {
        //add an order
        Order order = new Order();

        //add items to orderItem
        List<Item> cartItems =  user.getCart();

        if(!cartItems.isEmpty()){
            List<OrderItem> orderItems = new ArrayList<>();

            for (Item cartItem : cartItems){
                Item orderItem = cartItem;
                order.setOrderItem(orderItem);
            }
            orderRepo.save(order);
            ((th.mfu.domain.User) user).setOrder(order);
            user.getCart().clear();
            userRepo.save(user);
        }

        /* 
        for (int i = 0, i< buyer.get().getCart().size(), i++) { //***************************** 
            OrderItem orderItem = new OrderItem();
            orderItem = itemRepo.findById(buyer.getCart().get(i).getId());
            order.setOrderItem(orderItem);
        }
        buyer.setOrder(order);
        */

        //set a rider randomly

        //how?????? (Math.random or smth like that!!!!)

        return ""; // thank you page
   }













   //////////////////////////////Seller

   //to create seller account
    @GetMapping
    public String addASellerSingupForm(Model model){
        model.addAttribute("seller", new Seller());
        return "";
    }

    //to save seller account
    @PostMapping
    public String saveSeller(@ModelAttribute Buyer buyer){
        buyerRepo.save(buyer);
        return"";
    }

    //Check line 94
    //to view the seller's shop menu
    @GetMapping("")
    public String viewShopMenu(@PathVariable Long id, Model model) {
        model.addAttribute("shopItems", itemRepo.findAllById(id));
        return "";
    }

    //to create new item
    @GetMapping("")
    public String addItemCreateForm(Model model) {
        model.addAttribute("item", new Item());
        return "";
    }

    //to save new item
    @PostMapping("")
    // id is seller id
    public String saveItem(@ModelAttribute Item item, @PathVariable Long id) {
        Restaurant restaurant = restaurantRepo.findById(id); 
        restaurant.setItem(item);
        itemRepo.save(item);
        return "";
    }

    //to delete item from shop menu
    @GetMapping("")
    public String deleteItem(@PathVariable Long id) {
        itemRepo.deleteById(id);
        return "";
    }

    //to show orders
    @GetMapping("")
    public String showOrders(@PathVariable Long id, Model model) {
        model.addAttribute("orders", orderRepo.findAllById(id));
        return "";
    }


    //accepting order is enough with just HTML

    //to deny customer's order
    @GetMapping("")
    // id is item id
    public String denyOrder(@PathVariable Long id) {
        orderRepo.deleteById(id);
        return "";
    }



}