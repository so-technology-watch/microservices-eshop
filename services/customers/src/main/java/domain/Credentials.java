package domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean representing the credentials of a customer.
 * 
 * @author guillaume
 *
 */
public class Credentials implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 9071465228742853029L;
    /**
     * Email of the customer
     */
    private String email;
    /**
     * PassWord of the customer
     */
    private String passWord;

    /**
     * Empty constructor.
     */
    public Credentials() {
    }

    /**
     * Creates a new instance of the class.
     * 
     * @param email
     * @param passWord
     */
    public Credentials(String email, String passWord) {

	this.email = email;
	this.passWord = passWord;
    }

    /**
     * 
     * @return email of type String.
     */
    @JsonProperty
    public String getEmail() {

	return email;
    }

    /**
     * 
     * @param email
     */
    public void setEmail(String email) {

	this.email = email;
    }

    /**
     * 
     * @return passWord of type String.
     */
    @JsonProperty
    public String getPassWord() {

	return passWord;
    }

    /**
     * 
     * @param passWord
     */
    public void setPassWord(String passWord) {

	this.passWord = passWord;
    }

}
