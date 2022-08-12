package bg.project.letscook.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {
    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/data_policy")
    public String dataPolicy() {
        return "data_policy";
    }

    @GetMapping("/terms_of_use")
    public String termsOfUse() {
        return "terms_of_use";
    }
}
