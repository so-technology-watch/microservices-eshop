package domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean representing a customer.
 * 
 * @author guillaume
 *
 */
public class Customer implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5037328775785059700L;
    /**
     * unique id of the customer.
     */
    private String id;
    /**
     * Customer's first name.
     */
    private String firstname;
    /**
     * Customer's lastname.
     */
    private String lastname;
    /**
     * Customer's email.
     */
    private String email;
    /**
     * customer's credentials.
     */
    private Credentials credentials;
    /**
     * Customer's address
     */
    private String address;
    /**
     * Customer's phone number.
     */
    private String phoneNumber;

    /**
     * Empty constructor.
     */
    public Customer() {
    }

    /**
     * Creates a new instance of the customer class.
     * 
     * @param id
     * @param firstname
     * @param lastname
     * @param email
     * @param address
     * @param phoneNumber
     * @param credentials
     */
    public Customer(String id, String firstname, String lastname, String email, String address, String phoneNumber,
	    Credentials credentials) {
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.address = address;
	this.phoneNumber = phoneNumber;
	this.credentials = credentials;
    }

    /**
     * 
     * @return id of type int.
     */
    @JsonProperty
    public String getId() {

	return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(String id) {

	this.id = id;
    }

    /**
     * 
     * @return firstname of type String.
     */
    @JsonProperty
    public String getFirstname() {

	return firstname;
    }

    /**
     * 
     * @param firstname
     */
    public void setFirstname(String firstname) {

	this.firstname = firstname;
    }

    /**
     * 
     * @return lastname of type String.
     */
    @JsonProperty
    public String getLastname() {

	return lastname;
    }

    /**
     * 
     * @param lastname
     */
    public void setLastname(String lastname) {

	this.lastname = lastname;
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
     * @return address of type String.
     */
    @JsonProperty
    public String getAddress() {

	return address;
    }

    /**
     * 
     * @param address
     */
    public void setAddress(String address) {

	this.address = address;
    }

    /**
     * 
     * @return phone number of type String.
     */
    @JsonProperty
    public String getPhoneNumber() {

	return phoneNumber;
    }

    /**
     * 
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {

	this.phoneNumber = phoneNumber;
    }

    /**
     * 
     * @return credentials of type Credentials.
     */
    public Credentials getCredentials() {

	return credentials;
    }

    /**
     * 
     * @param credentials
     */
    public void setCredentials(Credentials credentials) {

	this.credentials = credentials;
    }
}
