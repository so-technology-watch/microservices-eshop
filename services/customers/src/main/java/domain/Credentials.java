package domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

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
     * Password of the customer
     */
    private String password;

    /**
     * Salt used to generate the hashed password
     */
    @JsonIgnore
    private String salt;

    /**
     * Empty constructor.
     */
    public Credentials() {
    }

    /**
     * Creates a new instance of the class.
     * 
     * @param email
     * @param password
     */
    public Credentials(String email, String password) {

	this.email = email;
	this.password = password;
    }

    /**
     * 
     * @return email of type String.
     */
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
     * @return password of type String.
     */
    @JsonIgnore
    public String getPassword() {

	return password;
    }

    /**
     * 
     * @param password
     */
    public void setPassword(String password) {

	this.password = password;
    }

    public String getSalt() {

	return salt;
    }

    public void setSalt(String salt) {

	this.salt = salt;
    }

}
