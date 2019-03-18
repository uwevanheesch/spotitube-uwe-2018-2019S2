package nl.han.oose.resources;

import nl.han.oose.dto.ErrorDTO;
import nl.han.oose.dto.TokenDTO;
import nl.han.oose.dto.UserDTO;
import nl.han.oose.persistence.UserDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    private UserDAO userDAO = new UserDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {

        UserDTO authenticatedUser = userDAO.getUser(user.getUser(), user.getPassword());

        if (authenticatedUser != null) {
            return Response.ok(new TokenDTO("1234", authenticatedUser.getName())).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorDTO("Login failed for user " + user.getUser()))
                    .build();
        }
    }

}
