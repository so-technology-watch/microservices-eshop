package domain;

public class AuthStatus {

    private int code;
    private int message;

    public AuthStatus(int code, int message) {
	this.code = code;
	this.message = message;
    }

    public int getCode() {

	return code;
    }

    public void setCode(int code) {

	this.code = code;
    }

    public int getMessage() {

	return message;
    }

    public void setMessage(int message) {

	this.message = message;
    }

}
