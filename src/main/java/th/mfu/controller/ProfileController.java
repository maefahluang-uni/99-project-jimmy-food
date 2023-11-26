package th.mfu.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import th.mfu.domain.User;
import th.mfu.repository.UserRepository;

@Controller
public class ProfileController {

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

        // Save the updated user to the databaseee
        userRepository.save(currentUser);

        return "redirect:/profile";
    }
}
