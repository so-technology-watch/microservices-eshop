package elements;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing the authentification status
 * 
 * @author guillaume
 *
 */
public class AuthStatus {

    /**
     * Code corresponding to the case where the customer is authenticated.
     */
    public static final int CODE_AUTH = 0;
    /**
     * Code corresponding to the case where the customer is not authenticated.
     */
    public static final int CODE_NOT_AUTH = 1;

    /**
     * Message corresponding to the case where the customer is authenticated.
     */
    public static final String MSG_AUTH = "Customer is authentificated.";
    /**
     * Message corresponding to the case chere the customer is not
     * authenticated.
     */
    public static final String MSG_NOT_AUTH = "Customer is not authentificated.";

    /**
     * The code of the authentication status
     */
    private int code;
    /**
     * The message of the authentication status
     */
    private String message;

    /**
     * Empty constructor
     */
    public AuthStatus() {
    }

    /**
     * Creates a new instance of the authStatus class
     * 
     * @param code
     * @param message
     */
    public AuthStatus(int code, String message) {
	this.code = code;
	this.message = message;
    }

    /**
     * 
     * @return authStatus code of type int
     */
    @JsonProperty
    public int getCode() {

	return code;
    }

    /**
     * 
     * @param code
     */
    public void setCode(int code) {

	this.code = code;
    }

    /**
     * 
     * @return message of type string
     */
    @JsonProperty
    public String getMessage() {

	return message;
    }

    /**
     * 
     * @param message
     */
    public void setMessage(String message) {

	this.message = message;
    }

}
