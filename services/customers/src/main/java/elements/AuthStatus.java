package elements;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthStatus {
    
    public static final int CODE_AUTH = 0;

    
    public static final String MSG_AUTH = "Customer is authentificated.";


    private int code;
    private int message;
    
    
    public AuthStatus() {
    }

    public AuthStatus(int code, int message) {
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
    public int getMessage() {

	return message;
    }

    public void setMessage(int message) {

	this.message = message;
    }

}
