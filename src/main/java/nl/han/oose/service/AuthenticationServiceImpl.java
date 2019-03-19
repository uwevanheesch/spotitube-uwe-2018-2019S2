package nl.han.oose.service;

import nl.han.oose.dto.TokenDTO;
import nl.han.oose.dto.UserDTO;
import nl.han.oose.persistence.UserDAO;
import nl.han.oose.util.TokenGenerator;

import javax.enterprise.inject.Default;

@Default
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserDAO userDAO = new UserDAO();
    private TokenGenerator tokenGenerator = new TokenGenerator();

    @Override
    public TokenDTO login(String username, String password) {
        UserDTO user = userDAO.getUser(username, password);
        if (user != null) {
            return new TokenDTO(tokenGenerator.generateToken(), user.getName());
        } else {
            throw new SpotitubeLoginException("Login failed for user " + username);
        }
    }


}
