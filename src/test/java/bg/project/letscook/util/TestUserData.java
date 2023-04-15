package bg.project.letscook.util;

import bg.project.letscook.model.user.LetsCookUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TestUserData implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new LetsCookUserDetails(1L,
                "secret",
                username,
                "Martin",
                "Martinov",
                Collections.emptyList());
    }
}
