package com.example.projetoAluguel.ViewControllers.login;

import com.example.projetoAluguel.security.cookie.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/sair")
public class LogoutViewController {

    @GetMapping
    public String logout(HttpServletResponse response) throws IOException {
        CookieService.setCookie(response, "funcionarioId", "", 0);
        CookieService.setCookie(response, "funcionarioNome", "", 0);
        CookieService.setCookie(response, "funcionarioFuncao", "", 0);
        return "redirect:/login";
    }
}
