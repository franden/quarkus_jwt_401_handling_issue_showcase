package org.acme.rest.boundary;

import static org.acme.rest.control.JsonUtils.firstNotNull;

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
    public static final String CLASS_QUERY_PARAMETER = "classQueryParameter";
    public static final String CLASS_PATH_PARAMETER = "classPathParameter";
    public static final String METHOD_QUERY_PARAMETER = "methodQueryParameter";

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
            .add(CLASS_QUERY_PARAMETER, firstNotNull(classQueryParameter, ""))
            .add(CLASS_PATH_PARAMETER, firstNotNull(classPathParameter, ""))
            .add(METHOD_QUERY_PARAMETER, firstNotNull(methodQueryParameter, ""))
            .add("methodPathParameter", firstNotNull(methodPathParameter, ""))
            .build().toString();

    }


}
