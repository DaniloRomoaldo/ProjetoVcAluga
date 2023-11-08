package com.example.projetoAluguel.model.ViewControllers.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/home")
public class HomeViewController {

        @GetMapping()
        public String homePage() {
            return "home";
        }

}
