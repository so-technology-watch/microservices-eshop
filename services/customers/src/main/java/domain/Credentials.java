package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Credentials {

    private String email;
    private String passWord;

    public Credentials() {
    }

    public Credentials(String email, String passWord) {

	this.email = email;
	this.passWord = passWord;
    }

    @JsonProperty
    public String getEmail() {

	return email;
    }

    public void setEmail(String email) {

	this.email = email;
    }

    @JsonProperty
    public String getPassWord() {

	return passWord;
    }

    public void setPassWord(String passWord) {

	this.passWord = passWord;
    }

}
