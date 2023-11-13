package com.example.projetoAluguel.ViewControllers.login;

import com.example.projetoAluguel.domains.funcionario.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/login"})
public class LoginViewController {

    @Autowired
    private FuncionarioRepository repositoryFuncionario;

    @GetMapping()
    public String login(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }



//    @PostMapping()
//    public String loginRequestForm(@ModelAttribute Login login, Model model, HttpServletResponse response) throws IOException {
//        model.addAttribute("login", login);
//        Funcionario funcionario = repositoryFuncionario.findByCodFuncionarioAndNome(login.getCod(), login.getNome());
//
//        if (funcionario != null){
//            int tempoLogin = (60*30);
//            CookieService.setCookie(response, "funcionarioId", String.valueOf(funcionario.getId()), tempoLogin);
//            CookieService.setCookie(response, "funcionarioNome", String.valueOf(funcionario.getNome()), tempoLogin);
//            CookieService.setCookie(response, "funcionarioFuncao", String.valueOf(funcionario.getFuncao()), tempoLogin);
//
//            return "redirect:/home";
//        }
//        return "redirect:/login?error";
//}

}
