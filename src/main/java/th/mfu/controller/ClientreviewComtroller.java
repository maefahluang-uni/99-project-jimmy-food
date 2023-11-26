package th.mfu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import th.mfu.controller.FoodController.ReviewService;
import th.mfu.domain.Review;
import th.mfu.repository.ReviewRepository;

@Controller
public class ClientreviewComtroller {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review")
    public String Review(@ModelAttribute Review review) {
        // Process and save user data using UserService
        reviewService.saveReview(review);
        // Redirect to a success page or login page
        return "redirect:/index.html";
    }   
}