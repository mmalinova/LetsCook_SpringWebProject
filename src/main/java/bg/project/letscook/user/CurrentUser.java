package bg.project.letscook.user;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private String name;
    private boolean loggedIn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean isAnonymous() {
        return !isLoggedIn();
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void login(String userName) {
        this.name = userName;
        this.loggedIn = true;
    }

    public void clear() {
        this.name = null;
        this.loggedIn = false;
    }
}
