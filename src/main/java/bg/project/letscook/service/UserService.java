package bg.project.letscook.service;

import bg.project.letscook.model.dto.UserRegisterDTO;
import bg.project.letscook.model.entity.UserEntity;
import bg.project.letscook.model.mapper.UserMapper;
import bg.project.letscook.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailsService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserMapper userMapper,
            UserDetailsService userDetailsService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {
        UserEntity userToRegister = userMapper.userDtoToUserEntity(userRegisterDTO);
        userToRegister.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userToRegister = userRepository.save(userToRegister);
        //login
        login(userToRegister);
    }

    private void login(UserEntity userEntity) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(auth);
    }
}
