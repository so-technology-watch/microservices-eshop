export class Credentials {

  constructor(
    private email: String,
    private password: String
  ) { }

  getEmail() {
  	return this.email;
  }

  getPassword() {
  	return this.password;
  }

  setEmail(email : string) {
  	this.email = email;
  }
}
