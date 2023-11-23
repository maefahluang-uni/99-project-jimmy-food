package th.mfu.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ko {
            @GetMapping("/index")
            public String showindex(){
                return "Helllo";
            }
}
