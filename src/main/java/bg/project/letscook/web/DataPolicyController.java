package bg.project.letscook.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataPolicyController {
    @GetMapping("/data_policy")
    public String dataPolicy() {
        return "data_policy";
    }
}
