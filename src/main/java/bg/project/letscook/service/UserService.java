package bg.project.letscook.service;

import bg.project.letscook.model.dto.UserLoginDTO;
import bg.project.letscook.model.dto.UserRegisterDTO;
import bg.project.letscook.model.entity.UserEntity;
import bg.project.letscook.repository.UserRepository;
import bg.project.letscook.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> user = userRepository.findByEmail(userLoginDTO.getEmail());
        if (user.isEmpty()) {
            LOGGER.info("User with email " + userLoginDTO.getEmail() + " does no exist!");
            return false;
        }
        String rawPass = userLoginDTO.getPassword();
        String encodedPass = user.get().getPassword();

        boolean success = passwordEncoder.matches(rawPass, encodedPass);

        if (success) {
            //login
            currentUser.login(user.get().getFirstName());
        } else {
            logout();
        }
        return success;
    }

    public void logout() {
        currentUser.clear();
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {
        UserEntity userToRegister = new UserEntity();
        userToRegister.setEmail(userRegisterDTO.getEmail());
        userToRegister.setActive(true);
        userToRegister.setFirstName(userRegisterDTO.getFirstName());
        userToRegister.setLastName(userRegisterDTO.getLastName());
        userToRegister.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userToRegister = userRepository.save(userToRegister);

        //login
        currentUser.login(userToRegister.getFirstName());
    }
}
