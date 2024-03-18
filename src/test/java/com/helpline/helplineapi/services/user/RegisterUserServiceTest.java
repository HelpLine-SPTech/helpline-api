package com.helpline.helplineapi.services.user;

import com.helpline.helplineapi.HelplineApiApplication;
import com.helpline.helplineapi.data.contract.user.auth.register.RegisterRequest;
import com.helpline.helplineapi.entities.user.UserEntity;
import com.helpline.helplineapi.enums.UserRole;
import com.helpline.helplineapi.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = HelplineApiApplication.class)
@AutoConfigureMockMvc
public class RegisterUserServiceTest {

    @InjectMocks
    private RegisterUserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void returnSuccess_WhenRegister() {
        var request = new RegisterRequest("", "", UserRole.ADMIN);

        when(repository.findByEmail(anyString()))
                .thenReturn(null);

        doCallRealMethod()
                .when(passwordEncoder)
                .encode(anyString());

        when(repository.save(any(UserEntity.class)))
                .thenReturn(new UserEntity("", "", UserRole.USER));

        var response = service.process(request);

        assertEquals(response.getBody().getSuccess(), true);
    }

}
