package bg.project.letscook.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoginPageShown() throws Exception {
        mockMvc.perform(get("/user/login")).andExpect(status().isOk()).andExpect(view().name("login"));
    }

    @Test
    void testRegisterPageShown() throws Exception {
        mockMvc.perform(get("/user/register")).andExpect(status().isOk()).andExpect(view().name("register"));
    }

    @Test
    void testRegisterRedirect() throws Exception {
        mockMvc.perform(post("/user/register").
                        param("firstName", "Marti").
                        param("lastName", "Martinov").
                        param("email", "m@abv.bg").
                        param("password", "123").
                        param("repeatPass", "1").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/user/register"));
    }

    @Test
    void testRegister() throws Exception {
        mockMvc.perform(post("/user/register").
                        param("firstName", "Marti").
                        param("lastName", "Martinov").
                        param("email", "m@abv.bg").
                        param("password", "123").
                        param("repeatPass", "123").
                        with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/user/register"));
    }
}
