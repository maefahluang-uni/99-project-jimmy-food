package th.mfu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

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