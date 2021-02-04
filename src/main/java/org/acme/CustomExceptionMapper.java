package org.acme;

import io.quarkus.security.UnauthorizedException;
import javax.annotation.Priority;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(1)
public class CustomExceptionMapper implements ExceptionMapper<UnauthorizedException> {

    @Override
    public Response toResponse(io.quarkus.security.UnauthorizedException e) {
        System.out.println("CustomExceptionMapper UnauthorizedException was invoked " + e);
        return Response.serverError().build();
    }
}
