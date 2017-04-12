package domain;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class AuthToken {

    private int customerID;
    private long timestamp;

    public AuthToken(int customerID, long timestamp) {
	
	this.customerID = customerID;
	this.timestamp = timestamp;
    }

    public String toJWTToken() {

	String token = "";

	try {
	    Algorithm algorithm = Algorithm.HMAC256(customerID + timestamp + "");
	    token = JWT.create().withIssuer("auth0").sign(algorithm);
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}

	return token;
    }

    public int getCustomerID() {

	return customerID;
    }

    public void setCustomerID(int customerID) {

	this.customerID = customerID;
    }

    public long getTimestamp() {

	return timestamp;
    }

    public void setTimestamp(long timestamp) {

	this.timestamp = timestamp;
    }

}
