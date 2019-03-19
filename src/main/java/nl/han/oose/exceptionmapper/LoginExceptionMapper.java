package nl.han.oose.exceptionmapper;

import nl.han.oose.dto.ErrorDTO;
import nl.han.oose.service.SpotitubeLoginException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LoginExceptionMapper implements ExceptionMapper<SpotitubeLoginException> {
    @Override
    public Response toResponse(SpotitubeLoginException message) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorDTO(message.getMessage()))
                .build();
    }
}
