package th.mfu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.User;
import th.mfu.repository.UserRepository;

@RestController
public class LoginController {

@Autowired
private UserRepository userRepo;

@PostMapping("/#")
    public String handlePostRequest(@RequestBody UserRepository request) {
        // Your logic here
        return "your-view-name";
    }
}
