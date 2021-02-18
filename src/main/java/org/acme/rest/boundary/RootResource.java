package org.acme.rest.boundary;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

@RequestScoped
@Path("rest")
public class RootResource {

    @Inject
    ParameterResource parameterResource;

    @Path("parameter")
    public ParameterResource getParameterResource() {
        return parameterResource;
    }

}
