package nl.han.oose.service;

import nl.han.oose.dto.TokenDTO;

/**
 * Created by uwe@vanheesch.net on 19.03.19.
 */
public interface AuthenticationService {
    TokenDTO login(String username, String password);
}
