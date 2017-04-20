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
/**
 * Resource dealing with the authentication.
 * 
 * @author guillaume
 *
 */
public class AuthResource {

    /**
     * Service dealing with the authentication.
     */
    private AuthService authService;

    /**
     * 
     * @param authService
     */
    public AuthResource(AuthService authService) {
	super();
	this.authService = authService;
    }

    @GET
    @Path("/{token}")
    /**
     * Handles the GET method.
     * 
     * @param token
     * @return authStatus
     */
    public AuthStatus getAuthStatus(@PathParam("token") String token) {

	return authService.retreiveAuthStatus(token);

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Handles the POST method.
     * 
     * @param credentials
     * @return authToken or an error of type string.
     */
    public String authentification(Credentials credentials) {

	return authService.authentification(credentials);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{token}")
    /**
     * 
     * @param token
     * @return message of type string or an error of type string
     */
    public String disconnect(@PathParam("token") String token) {

	return authService.deleteAuthToken(token);
    }

    /**
     * 
     * @return AuthService
     */
    public AuthService getAuthService() {

	return authService;
    }

    /**
     * 
     * @param authService
     */
    public void setAuthService(AuthService authService) {

	this.authService = authService;
    }

}
