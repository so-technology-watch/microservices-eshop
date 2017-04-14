package elements;

import com.google.gson.Gson;

public class Error {

    public static final int CODE_NOT_FOUND = 1;
    public static final int CODE_PWD_MISMATCH = 2;

    public static final String MSG_NOT_FOUND = "Could not find matching customer.";
    public static final String MSG_PWD_MISMATCH = "The given password did not match the stored password.";

    private int code;
    private String msg;

    public Error(int code, String msg) {
	this.code = code;
	this.msg = msg;
    }

    public String toJson() {

	return new Gson().toJson(this);
    }

    public int getCode() {

	return code;
    }

    public void setCode(int code) {

	this.code = code;
    }

    public String getMsg() {

	return msg;
    }

    public void setMsg(String msg) {

	this.msg = msg;
    }

}
