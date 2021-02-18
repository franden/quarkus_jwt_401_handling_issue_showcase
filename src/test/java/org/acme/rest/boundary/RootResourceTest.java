package org.acme.rest.boundary;

import static io.restassured.RestAssured.given;
import static org.acme.rest.boundary.RootResource.CLASS_ROOT_PATH_PARAMETER;
import static org.acme.rest.boundary.RootResource.CLASS_ROOT_QUERY_PARAMETER;
import static org.acme.rest.boundary.RootResource.METHOD_ROOT_PATH_PARAMETER;
import static org.acme.rest.boundary.RootResource.METHOD_ROOT_QUERY_PARAMETER;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import javax.ws.rs.core.Response.Status;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

@TestHTTPEndpoint(RootResource.class)
@QuarkusTest
class RootResourceTest {

    @Test
    void testParameter() {
        String queryParameter = "parameter_query";
        String pathParameter = "parameter_path";
        Response response = given()
            .queryParam(RootResource.ROOT_QUERY_PARAMETER, queryParameter)
            .when()
            .redirects().follow(false)
            .get(pathParameter);

        System.out.println(response.getBody().prettyPrint());

        response.then()
            .statusCode(Status.OK.getStatusCode())
            .body(CLASS_ROOT_QUERY_PARAMETER, CoreMatchers.equalTo(queryParameter))
            .body(METHOD_ROOT_QUERY_PARAMETER, CoreMatchers.equalTo(queryParameter))
            .body(CLASS_ROOT_PATH_PARAMETER, CoreMatchers.equalTo(pathParameter))
            .body(METHOD_ROOT_PATH_PARAMETER, CoreMatchers.equalTo(pathParameter))
        ;
    }
}