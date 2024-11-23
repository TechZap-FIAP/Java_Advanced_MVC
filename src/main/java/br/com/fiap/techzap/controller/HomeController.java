package br.com.fiap.techzap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"", "/", "home"})
    public String home() {
        return "index";
    }

    @GetMapping("about")
    public String about() {
        return "pages/about";
    }

    @GetMapping("team")
    public String team() {
        return "pages/team";
    }

}
