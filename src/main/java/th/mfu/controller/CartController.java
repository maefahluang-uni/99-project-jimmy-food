package th.mfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.Cart;
import th.mfu.repository.CartRepository;


@RestController
    public class CartController {   

    @Autowired
    private CartRepository cartRepo;
    
    @PostMapping("/confirmOrder")
    public ResponseEntity<String> confirmOrder(@RequestBody Cart cart) {
        try {

            int total_price = cart.calculateTotalPrice();
            // Save the order to the database
            cartRepo.saveAndFlush(cart);
            return ResponseEntity.ok("Order confirmed successfully! Total Price: " + total_price + " baht");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to confirm order");
        }
    }
}