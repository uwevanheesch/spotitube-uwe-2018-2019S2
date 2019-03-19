package nl.han.oose.service;

import nl.han.oose.dto.TokenDTO;

import javax.enterprise.inject.Alternative;

@Alternative
public class LocalAuthenticationService implements AuthenticationService {

    @Override
    public TokenDTO login(String username, String password) {
        return new TokenDTO("1234", username);
    }
}
