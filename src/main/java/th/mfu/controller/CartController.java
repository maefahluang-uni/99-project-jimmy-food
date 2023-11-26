package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.controller.FoodController.CartService;
import th.mfu.domain.Cart;
import th.mfu.repository.CartRepository;


@RestController
    public class CartController {   

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartService cartService;
    
    @PostMapping("/confirmOrder")
    public String confirmOrder(@ModelAttribute Cart cart) {

            // Save the order to the database
            cartService.saveCart(cart);
            return "redirect:/waiting.html";
    }
}