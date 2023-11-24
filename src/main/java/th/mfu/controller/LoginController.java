package th.mfu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import th.mfu.domain.User;
import th.mfu.repository.UserRepository;

@Controller
public class LoginController {

@Autowired
private UserRepository userRepo;

@GetMapping("/signup")
    public String showSignupPage() {
        return "signup";
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // User
    private final Map<String, User> users = new HashMap<>();

    // to create User account
    @GetMapping("/registerUser")
    public String registerUser(@RequestParam String id,
    @RequestParam String password,
    Model model) {

        User user = new User(id, password);
        users.put(id, user);
        model.addAttribute("message", "User registered successfully!");
        model.addAttribute("user", user);
        return "registration-success";
    }


    @GetMapping("/loginUser")
    public String loginUser(@RequestParam String id,
                            @RequestParam String password,
                            Model model) {

        User user = users.get(id);

        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("message", "Login successful!");
            model.addAttribute("user", user);
            return "login-success";
        } else {
            model.addAttribute("message", "Login failed. Please check your credentials.");
            return "login-failure";
        }
    }

    // to save User account
    public User saveUser(User user) {
        // You can add validation logic or other business logic here before saving
        return userRepo.save(user);
    }
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @RequestMapping("/user")
public class UserController {

    @GetMapping("/register")
    public String showRegistrationForm(User user) {
        return "registration";
    }

public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        // You can add validation logic here
        model.addAttribute("message", "User registered successfully!");
        model.addAttribute("user", user);
        return "registration-success";
    }
}
@PostMapping("/signup")
public String signup(@RequestParam String id, @RequestParam String password, Model model) {
    try {
        User user = new User(id, password);
        saveUser(user);
        model.addAttribute("message", "User registered successfully!");
        model.addAttribute("user", user);
        return "registration-success";
    } catch (Exception e) {
        model.addAttribute("message", "Error registering user: " + e.getMessage());
        return "registration-failure";
    }
}
}
