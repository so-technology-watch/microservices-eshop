package elements;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthStatus {
    
    public static final int CODE_AUTH = 0;
    public static final int CODE_NOT_AUTH = 1;

    
    public static final String MSG_AUTH = "Customer is authentificated.";
    public static final String MSG_NOT_AUTH = "Customer is not authentificated.";


    private int code;
    private String message;
    
    
    public AuthStatus() {
    }

    public AuthStatus(int code, String message) {
	this.code = code;
	this.message = message;
    }

    @JsonProperty
    public int getCode() {

	return code;
    }

    public void setCode(int code) {

	this.code = code;
    }
    
    @JsonProperty
    public String getMessage() {

	return message;
    }

    public void setMessage(String message) {

	this.message = message;
    }

}
