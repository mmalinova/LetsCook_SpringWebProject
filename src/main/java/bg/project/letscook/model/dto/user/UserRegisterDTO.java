package bg.project.letscook.model.dto.user;

import bg.project.letscook.model.validation.MatchPasswords;
import bg.project.letscook.model.validation.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MatchPasswords (first = "password", second = "repeatPass", message = "Паролите не съвпадат")
public class UserRegisterDTO {
    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;
    @NotBlank(message = "Моля, въведи Email")
    @Email(message = "Невалиден Email")
    @UniqueEmail(message = "Потребител с такъв Email вече съществува")
    private String email;
    @NotBlank
    @Size(min = 3)
    private String password;
    @NotBlank
    @Size(min = 3)
    private String repeatPass;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPass() {
        return repeatPass;
    }

    public void setRepeatPass(String repeatPass) {
        this.repeatPass = repeatPass;
    }
}
