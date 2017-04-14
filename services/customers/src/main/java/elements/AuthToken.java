package elements;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class AuthToken {

    private int customerID;

    public AuthToken() {
    }

    public AuthToken(String token) {

	decodeToken(token);

    }

    public AuthToken(int customerID) {

	this.customerID = customerID;
    }

    public String encodeToJWT() {

	String token = "";

	try {
	    Algorithm algorithm = Algorithm.HMAC256("algo");
	    token = JWT.create().withIssuer("auth0").withClaim("customerID", customerID).sign(algorithm);
	} catch (UnsupportedEncodingException | IllegalArgumentException e) {
	    e.printStackTrace();
	}

	return token;
    }

    public boolean verifyToken(String string) {

	try {
	    Algorithm algorithm = Algorithm.HMAC256("algo");
	    JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
	    DecodedJWT jwt = verifier.verify(string);
	    return true;

	} catch (IllegalArgumentException | UnsupportedEncodingException e) {
	    e.printStackTrace();
	    return false;

	}

    }

    private void decodeToken(String token) {

	DecodedJWT jwt = JWT.decode(token);

	this.customerID = jwt.getClaim("customerID").asInt();
	System.out.println(customerID);
    }

    public int getCustomerID() {

	return customerID;
    }

    public void setCustomerID(int customerID) {

	this.customerID = customerID;
    }

}
