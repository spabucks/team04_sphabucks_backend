package sphabucks.domain.paying.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/api/v1/portone")
public class payingController {

    @GetMapping("/pay")
    public String paying(Model model) {

        model.addAttribute("name", "스벅 텀블러");
        return "test.html";
    }

    @GetMapping("/re/")
    public String reTest(Model model,@RequestParam Map<String, Object> map) {

        model.addAttribute("name", "지욱이");

        log.info("@@@@@@@@@@@@@@@ {}", map);

        return "goFront.html";
    }


}
