package org.acme;

import io.quarkus.security.AuthenticationFailedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationFailedExceptionMapper implements ExceptionMapper<AuthenticationFailedException> {

    @Override
    public Response toResponse(AuthenticationFailedException e) {
        System.out.println("AuthenticationFailedException was thrown " + e);
        return null;
    }
}
