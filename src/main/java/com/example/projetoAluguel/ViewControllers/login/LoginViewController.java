package com.example.projetoAluguel.ViewControllers.login;

import com.example.projetoAluguel.domains.filial.Filial;
import com.example.projetoAluguel.domains.filial.FilialRepository;
import com.example.projetoAluguel.domains.funcionario.Funcionario;
import com.example.projetoAluguel.domains.funcionario.FuncionarioRepository;
import com.example.projetoAluguel.security.cookie.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping(value = {"/login"})
public class LoginViewController {

    @Autowired
    private FuncionarioRepository repositoryFuncionario;

    @Autowired
    private FilialRepository filialRepository;

    @GetMapping()
    public String login(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }


    @PostMapping()
    public String loginRequestForm(@ModelAttribute Login login, Model model, HttpServletResponse response) throws IOException {
        model.addAttribute("login", login);
        Funcionario funcionario = repositoryFuncionario.findByCodFuncionarioAndNome(login.getCod(), login.getNome().toUpperCase());

        if (funcionario != null){
            Filial filial = filialRepository.findByNome(funcionario.getFilial().getNome());
            int tempoLogin = (60*60*30);
            CookieService.setCookie(response, "funcionarioId", String.valueOf(funcionario.getId()), tempoLogin);
            CookieService.setCookie(response, "funcionarioNome", String.valueOf(funcionario.getNome()), tempoLogin);
            CookieService.setCookie(response, "funcionarioFuncao", String.valueOf(funcionario.getFuncao()), tempoLogin);
            CookieService.setCookie(response, "filialAtual", String.valueOf(filial.getNome()), tempoLogin);

            return "redirect:/home";
        }
        return "redirect:/login?error";
}

}
