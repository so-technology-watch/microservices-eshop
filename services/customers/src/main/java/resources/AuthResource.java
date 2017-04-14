package resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.Credentials;
import elements.AuthStatus;
import services.AuthService;

@Path("api/v1/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private AuthService authService;

    public AuthResource(AuthService authService) {
	super();
	this.authService = authService;
    }

    @GET
    @Path("/{token}")
    public AuthStatus getAuthStatus(@PathParam("token") String token) {

	return authService.retreiveAuthStatus(token);

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String authentification(Credentials credentials) {

	return authService.authentification(credentials);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{token}")
    public String disconnect(@PathParam("token") String token) {

	return authService.deleteAuthToken(token);
    }

    public AuthService getAuthService() {

	return authService;
    }

    public void setAuthService(AuthService authService) {

	this.authService = authService;
    }

}
