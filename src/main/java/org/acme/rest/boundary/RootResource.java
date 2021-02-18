package org.acme.rest.boundary;

import static org.acme.rest.boundary.ParameterResource.QUERY_PARAMETER;
import static org.acme.rest.control.JsonUtils.firstNotNull;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.acme.rest.control.JsonUtils;

@RequestScoped
@Path("rest")
public class RootResource {

    private static final String ROOT_QUERY_PARAMETER = "rootQueryParameter";
    private static final String ROOT_PATH_PARAMETER = "rootPathParameter";

    @Inject
    ParameterResource parameterResource;

    @QueryParam(ROOT_QUERY_PARAMETER)
    String classRootQueryParameter;

    @PathParam(ROOT_PATH_PARAMETER)
    String classRootPathParameter;


    @Path("parameter")
    public ParameterResource getParameterResource() {
        return parameterResource;
    }

    @Path("{" + ROOT_PATH_PARAMETER + "}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String getParameter(
        @QueryParam(ROOT_QUERY_PARAMETER) String methodRootQueryParameter,
        @PathParam(ROOT_PATH_PARAMETER) String methodRootPathParameter) {
        System.out.println("getParameter called");
        return Json.createObjectBuilder()
            .add("classRootQueryParameter", firstNotNull(classRootQueryParameter, ""))
            .add("classRootPathParameter", firstNotNull(classRootPathParameter, ""))
            .add("methodRootQueryParameter", firstNotNull(methodRootQueryParameter, ""))
            .add("methodRootPathParameter", firstNotNull(methodRootPathParameter, ""))
            .build().toString();

    }





}
