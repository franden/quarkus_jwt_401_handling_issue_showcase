package org.acme;

import io.quarkus.smallrye.jwt.runtime.auth.JWTAuthMechanism;
import io.quarkus.vertx.http.runtime.security.ChallengeData;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Alternative;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;

@Alternative
@Priority(1)
@ApplicationScoped
public class JwtAuth extends JWTAuthMechanism {

    public void init403Handler(@Observes Router router) {
        router.route().failureHandler(failedRoutingContext -> {
            if (failedRoutingContext.statusCode() == Status.FORBIDDEN.getStatusCode()) {
                System.out.println("403 was recognized");
                failedRoutingContext.response().headers().add(HttpHeaders.LOCATION, "/somewhere");
                failedRoutingContext.response().setStatusCode(Status.FOUND.getStatusCode());
            }
            failedRoutingContext.response().end();
            System.out.println("failureHandler end");
        });
    }


    @Override
    public Uni<ChallengeData> getChallenge(RoutingContext context) {
        System.out.println("IN CHALLENGE");
        return Uni.createFrom().item(new ChallengeData(Status.FOUND.getStatusCode(), HttpHeaders.LOCATION, "/somewhere"));
    }
}