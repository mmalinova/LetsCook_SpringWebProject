package bg.project.letscook.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 3)
    private String password;

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

    @Override
    public String toString() {
        return "User{" +
                "username='" + email + '\'' +
                ", password='" + (password != null ? "PROVIDED" : null) + '\'' +
                '}';
    }
}
