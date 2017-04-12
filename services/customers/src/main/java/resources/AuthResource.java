package resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.AuthStatus;
import domain.Credentials;

@Path("api/v1/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @GET
    @Path("/{token}")
    public AuthStatus getAuthStatus(@PathParam("token") String token) {

	// TODO

	return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String authentification(Credentials credentials) {

	// TODO

	return null;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{token}")
    public String disconnect(@PathParam("token") String token) {

	// TODO

	return null;
    }

}
