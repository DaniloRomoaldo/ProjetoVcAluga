package com.example.projetoAluguel.ViewControllers.home;

import com.example.projetoAluguel.security.cookie.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/home")
public class HomeViewController {

        @GetMapping()
        public String homePage(Model model , HttpServletRequest request) {

            model.addAttribute("nome", CookieService.getCookie(request, "funcionarioNome"));
            model.addAttribute("funcao", CookieService.getCookie(request, "funcionarioFuncao"));
            return "home";
        }

}
