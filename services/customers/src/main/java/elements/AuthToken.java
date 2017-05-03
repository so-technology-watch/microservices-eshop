package elements;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * Class dealing with JWT tokens
 * 
 * @author guillaume
 *
 */
public class AuthToken {

    /**
     * id of the customer which is going to be used to generate the JWT token.
     */
    private String customerID;

    /**
     * Empty constructor
     */
    public AuthToken() {
    }

    /**
     * Constructor that takes in parameter a JWT Token.
     * 
     * @param token
     */

    /**
     * COnstructor that takes in parameter a customerID
     * 
     * @param customerID
     */
    public AuthToken(String customerID) {

	this.customerID = customerID;
    }

    /**
     * Creates a new JWT Token using the customerID
     * 
     * @return
     */
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

    /**
     * Decodes a JWT token.
     * 
     * @param token
     */
    public void decodeToken(String token) {

	try {

	    DecodedJWT jwt = JWT.decode(token);
	    this.customerID = jwt.getClaim("customerID").asString();
	    System.out.println(customerID);
	} catch (JWTDecodeException e) {

	    e.printStackTrace();
	    this.customerID = null;

	}

    }

    /**
     * 
     * @return customerID of type int.
     */
    public String getCustomerID() {

	return customerID;
    }

    /**
     * 
     * @param customerID
     */
    public void setCustomerID(String customerID) {

	this.customerID = customerID;
    }

}
