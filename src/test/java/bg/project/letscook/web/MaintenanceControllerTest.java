package bg.project.letscook.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class MaintenanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoginPageShown() throws Exception {
        mockMvc.perform(get("/maintenance")).andExpect(status().isOk()).andExpect(view().name("maintenance"));
    }
}
