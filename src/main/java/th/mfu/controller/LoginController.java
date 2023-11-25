package th.mfu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
=======
>>>>>>> f9d4a0c796e144dab5ad04913def49d284006783

import th.mfu.controller.FoodController.UserService;
import th.mfu.domain.User;
import th.mfu.repository.UserRepository;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepo;

<<<<<<< HEAD
@PostMapping("/#")
    public String handlePostRequest(@RequestBody UserRepository request) {
        // Your logic here
        return "your-view-name";
    }
}
=======
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute User user) {
        // Process and save user data using UserService
        userService.saveUser(user);
        // Redirect to a success page or login page
        return "redirect:/login.html";
    }   
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        // Find the user by username
        User existingUser = userRepo.findByUsername(user.getUsername());

        // Check if the user exists and the password is correct (without encryption)
        if (existingUser != null && user.getPassword().equals(existingUser.getPassword())) {
        return "userPersona";
            //return "userPersona";
        } else {
        // Add a failure message to the model
        model.addAttribute("loginFailed", true);
        return "login"; // Render the login page
        }
       /*  model.addAttribute("loginMessage", "Login failed");
        return "login-failed";
        } */
    }
}
>>>>>>> f9d4a0c796e144dab5ad04913def49d284006783
