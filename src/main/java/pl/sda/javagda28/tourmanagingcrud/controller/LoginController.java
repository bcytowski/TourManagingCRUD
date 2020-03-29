package pl.sda.javagda28.tourmanagingcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.javagda28.tourmanagingcrud.dto.UserForm;


@Controller
public class LoginController  {

    @GetMapping("/login")
    public String viewLoginPage(final ModelMap modelMap){
        modelMap.addAttribute("userForm", new UserForm());
        return "login";
    }
}
