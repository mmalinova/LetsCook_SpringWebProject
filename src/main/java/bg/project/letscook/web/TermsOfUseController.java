package bg.project.letscook.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TermsOfUseController {
    @GetMapping("/terms_of_use")
    public String termsOfUse() {
        return "terms_of_use";
    }
}
