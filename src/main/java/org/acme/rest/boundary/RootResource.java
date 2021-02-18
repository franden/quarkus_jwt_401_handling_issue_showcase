package org.acme.rest.boundary;

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

@RequestScoped
@Path("rest")
public class RootResource {

    public static final String ROOT_QUERY_PARAMETER = "rootQueryParameter";
    public static final String ROOT_PATH_PARAMETER = "rootPathParameter";
    public static final String CLASS_ROOT_QUERY_PARAMETER = "classRootQueryParameter";
    public static final String CLASS_ROOT_PATH_PARAMETER = "classRootPathParameter";
    public static final String METHOD_ROOT_QUERY_PARAMETER = "methodRootQueryParameter";
    public static final String METHOD_ROOT_PATH_PARAMETER = "methodRootPathParameter";
    public static final String PATH_PARAM_PARAMETER = "parameter";

    @Inject
    ParameterResource parameterResource;

    @QueryParam(ROOT_QUERY_PARAMETER)
    String classRootQueryParameter;

    @PathParam(ROOT_PATH_PARAMETER)
    String classRootPathParameter;


    @Path(PATH_PARAM_PARAMETER)
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
            .add(CLASS_ROOT_QUERY_PARAMETER, firstNotNull(classRootQueryParameter, ""))
            .add(CLASS_ROOT_PATH_PARAMETER, firstNotNull(classRootPathParameter, ""))
            .add(METHOD_ROOT_QUERY_PARAMETER, firstNotNull(methodRootQueryParameter, ""))
            .add(METHOD_ROOT_PATH_PARAMETER, firstNotNull(methodRootPathParameter, ""))
            .build().toString();

    }





}
