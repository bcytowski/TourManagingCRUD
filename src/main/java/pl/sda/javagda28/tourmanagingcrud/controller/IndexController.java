package pl.sda.javagda28.tourmanagingcrud.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.javagda28.tourmanagingcrud.repository.UserRepository;

@Controller
@RequiredArgsConstructor
public class IndexController {


    @GetMapping("/")
    public String viewIndexPage() {
        return "startup-page";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/dashboard")
    public String viewDashboard(){
        return "admin-dashboard";
    }
}
