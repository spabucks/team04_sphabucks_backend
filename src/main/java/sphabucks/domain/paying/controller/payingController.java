package sphabucks.domain.paying.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/portone")
public class payingController {

    @PostMapping("/pay")
    public String paying(Model model) {

        model.addAttribute("name", "스벅 텀블러");
        return "test.html";
    }

}
