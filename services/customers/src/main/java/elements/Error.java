package elements;

import com.google.gson.Gson;

/**
 * Class representing errors.
 * 
 * @author guillaume
 *
 */
public class Error {

    /**
     * Code corresponding to the case where no user is found.
     */
    public static final int CODE_NOT_FOUND = 1;
    /**
     * Code corresponding to the case where the given password doesn't match the
     * stored one.
     */
    public static final int CODE_PWD_MISMATCH = 2;

    /**
     * Message corresponding to the case where the customer is not found.
     */
    public static final String MSG_NOT_FOUND = "Could not find matching customer.";
    /**
     * Message corresponding to the case where the given password deosn't match
     * the stored one.
     */
    public static final String MSG_PWD_MISMATCH = "The given password did not match the stored password.";
    /**
     * The code of the error.
     */
    private int code;
    /**
     * The message of the error.
     */
    private String msg;

    /**
     * Creates a new error.
     * 
     * @param code
     * @param msg
     */
    public Error(int code, String msg) {
	this.code = code;
	this.msg = msg;
    }

    /**
     * Returns the error in JSON format.
     * 
     * @return
     */
    public String toJson() {

	return new Gson().toJson(this);
    }

    /**
     * 
     * @return code of type int.
     */
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
     * @return message of type string.
     */
    public String getMsg() {

	return msg;
    }

    /**
     * 
     * @param msg
     */
    public void setMsg(String msg) {

	this.msg = msg;
    }

}
