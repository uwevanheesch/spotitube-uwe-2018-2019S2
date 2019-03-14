package nl.han.oose.resources;

import nl.han.oose.dto.ErrorDTO;
import nl.han.oose.dto.TokenDTO;
import nl.han.oose.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginResourceTest {

    private LoginResource sut;

    @BeforeEach
    void setUp() {
        sut = new LoginResource();
    }

    @Test
    void loginSuccess() {
        UserDTO userDTO = new UserDTO("Uwe", "Uwepass");

        Response actualResult = sut.login(userDTO);
        assertEquals(Status.OK.getStatusCode(), actualResult.getStatus());

        TokenDTO actualToken = (TokenDTO) actualResult.getEntity();
        assertEquals("Uwe van Heesch", actualToken.getUser());
        assertEquals("1234", actualToken.getToken());
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