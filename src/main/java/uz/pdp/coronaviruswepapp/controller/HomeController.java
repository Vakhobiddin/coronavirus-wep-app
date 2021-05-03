package uz.pdp.coronaviruswepapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.coronaviruswepapp.servises.VirusDataServise;

@Controller
public class HomeController {

    @Autowired
    VirusDataServise virusDataServise;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("locationStates",virusDataServise.getAllLocationState());
        return "home";
    }
}
