package org.acme.rest.boundary;

import javax.enterprise.context.Dependent;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Dependent
public class ParameterResource {

    public static final String QUERY_PARAMETER = "queryParameter";
    public static final String PATH_PARAMETER = "pathParameter";

    @QueryParam(QUERY_PARAMETER)
    String classQueryParameter;

    @PathParam(PATH_PARAMETER)
    String classPathParameter;

    @Path("{" + PATH_PARAMETER + "}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String getParameter(
        @QueryParam(QUERY_PARAMETER) String methodQueryParameter,
        @PathParam(PATH_PARAMETER) String methodPathParameter) {
        System.out.println("getParameter called");
        return Json.createObjectBuilder()
            .add("classQueryParameter", firstNotNull(classQueryParameter, ""))
            .add("classPathParameter", firstNotNull(classPathParameter, ""))
            .add("methodQueryParameter", firstNotNull(methodQueryParameter, ""))
            .add("methodPathParameter", firstNotNull(methodPathParameter, ""))
            .build().toString();

    }

    <T> T firstNotNull(T first, T second) {
        if (first == null) {
            return second;
        } else {
            return first;
        }
    }
}
