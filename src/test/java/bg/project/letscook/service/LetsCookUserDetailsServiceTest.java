package bg.project.letscook.service;

import bg.project.letscook.model.entity.RoleEntity;
import bg.project.letscook.model.entity.UserEntity;
import bg.project.letscook.model.enums.RoleEnum;
import bg.project.letscook.model.user.LetsCookUserDetails;
import bg.project.letscook.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LetsCookUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private LetsCookUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new LetsCookUserDetailsService(mockUserRepo);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        // arrange
        UserEntity testUserEntity =
                new UserEntity().
                        setEmail("marti@example.com").
                        setPassword("secret").
                        setFirstName("Marti").
                        setLastName("Martinov").
                        setActive(true).
                        setImageURL("http://image.com/image").
                        setUserRoles(
                                Set.of(new RoleEntity().setRole(RoleEnum.USER))
                        );

        when(mockUserRepo.findByEmail(testUserEntity.getEmail())).
                thenReturn(Optional.of(testUserEntity));

        // act
        LetsCookUserDetails userDetails = (LetsCookUserDetails)
                toTest.loadUserByUsername(testUserEntity.getEmail());
        LetsCookUserDetails user = new LetsCookUserDetails(1L, "secret", "example@gmail.com", "Marti", "Martinov", Collections.emptyList());

        // assert
        Assertions.assertTrue(user.isAccountNonExpired());
        Assertions.assertTrue(user.isAccountNonLocked());
        Assertions.assertTrue(user.isCredentialsNonExpired());
        Assertions.assertTrue(user.isEnabled());
        Assertions.assertEquals(testUserEntity.getEmail(),
                userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getFirstName(), userDetails.getFirstName());
        Assertions.assertEquals(testUserEntity.getLastName(), userDetails.getLastName());
        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUserEntity.getFirstName() + " " + testUserEntity.getLastName(),
                userDetails.getFullName());


        var authorities = userDetails.getAuthorities();

        Assertions.assertEquals(1, authorities.size());

        var authoritiesIter = authorities.iterator();

        Assertions.assertEquals("ROLE_" + RoleEnum.USER.name(),
                authoritiesIter.next().getAuthority());
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {

        // arrange
        // NOTE: No need to arrange anything, mocks return empty optionals.

        // act && assert
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("non-existant@example.com")
        );
    }
}
