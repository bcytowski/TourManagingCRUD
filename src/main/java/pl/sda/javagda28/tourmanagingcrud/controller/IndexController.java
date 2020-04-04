package pl.sda.javagda28.tourmanagingcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    public String getIndexPage() {
        return "index";
    }
}
