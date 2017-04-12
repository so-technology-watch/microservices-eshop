package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthStatus {

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
