package nl.han.oose.resources;

import nl.han.oose.dto.ErrorDTO;
import nl.han.oose.dto.TokenDTO;
import nl.han.oose.dto.UserDTO;
import nl.han.oose.persistence.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LoginResourceTest {

    @Mock
    private UserDAO userDAOStub;

    @InjectMocks
    private LoginResource sut;
    private UserDTO mockedUser;

    @BeforeEach
    void setUp() {
        // You can still make use of the BeforeEach, but make sure you
        // do NOT create a new instance of sut, otherwise the mocks will
        // not be used.
        mockedUser = new UserDTO();
        mockedUser.setName("Test Testuser");
        mockedUser.setPassword("testpassword");
        mockedUser.setUser("testuser");
    }

    @Test
    void loginSuccess() {
        makeUserDAOMockReturnPreDefinedUserDTO();

        UserDTO userDTO = new UserDTO("testuser", "testpassword");
        Response actualResult = sut.login(userDTO);

        assertEquals(Status.OK.getStatusCode(), actualResult.getStatus());

        TokenDTO actualToken = (TokenDTO) actualResult.getEntity();
        assertEquals("Test Testuser", actualToken.getUser());
        assertEquals("1234", actualToken.getToken());
    }

    private void makeUserDAOMockReturnPreDefinedUserDTO() {
        Mockito.when(userDAOStub.getUser("testuser", "testpassword"))
                .thenReturn(mockedUser);
    }

    @Test
    void loginFailure() {
        UserDTO userDTO = new UserDTO("Uwe", "WrongPassword");

        Response actualResult = sut.login(userDTO);
        assertEquals(Status.UNAUTHORIZED.getStatusCode(), actualResult.getStatus());

        ErrorDTO actualDTO = (ErrorDTO) actualResult.getEntity();
        assertEquals("Login failed for user Uwe", actualDTO.getMessage());
    }


}