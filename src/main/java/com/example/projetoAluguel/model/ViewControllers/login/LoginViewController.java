package com.example.projetoAluguel.model.ViewControllers.login;

import com.example.projetoAluguel.model.funcionario.Funcionario;
import com.example.projetoAluguel.model.funcionario.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginViewController {

    @Autowired
    private FuncionarioRepository repositoryFuncionario;

    @GetMapping()
    public String login(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping()
    public String loginRequestForm(@ModelAttribute Login login, Model model) {
        model.addAttribute("login", login);
        Funcionario funcionario = repositoryFuncionario.findByCodFuncionarioAndNome(login.getCod(), login.getNome());

        if (funcionario != null){
            return "redirect:/home";
        }

        return "redirect:/login?error";
}

}
