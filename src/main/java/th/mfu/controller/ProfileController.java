package th.mfu.controller;

<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
=======
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import th.mfu.domain.User;
import th.mfu.repository.UserRepository;
>>>>>>> refs/remotes/origin/main

@Controller
public class ProfileController {

<<<<<<< HEAD
    // Display the profile page
    @GetMapping("/profile")
    public String showProfile(Model model, Object loggedInUser) {
        // Check if a user is logged in
        if (loggedInUser != null) {
            // For simplicity, use a fixed user profile
            model.addAttribute("name");
            model.addAttribute("address", "123 Street, City");
            model.addAttribute("phoneNumber", "555-1234");
            model.addAttribute("points", "100");
            return "profile";
        } else {
            // If not logged in, redirect to the login page
            return "redirect:/login";
        }
    }

    // Handle the form submission for updating the profile
    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam String editName, @RequestParam String editAddress, @RequestParam String editPhoneNumber) {
        try {
            // Implement logic to update the user profile (e.g., save to a database)
            // Use the provided parameters: editName, editAddress, editPhoneNumber
            return "redirect:/profile"; // Redirect to the profile page after updating
        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception or return an error page
            return "error"; // Return an error page or redirect to an error page
        }
    }
}
=======
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute User updatedUser, Principal principal) {
        String username = principal.getName();
        User currentUser = userRepository.findByUsername(username);

        // Update only the fields that are allowed to be changed
        currentUser.setName(updatedUser.getName());
        currentUser.setAddress(updatedUser.getAddress());
        currentUser.setPhoneNumber(updatedUser.getPhoneNumber());

        // Save the updated user to the database
        userRepository.save(currentUser);

        return "redirect:/profile";
    }
}
>>>>>>> refs/remotes/origin/main
