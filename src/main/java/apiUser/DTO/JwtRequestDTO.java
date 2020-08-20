package apiUser.DTO;

import java.io.Serializable;

public class JwtRequestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7607297006727127929L;
	private String email;
    private String pass;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
    
}
