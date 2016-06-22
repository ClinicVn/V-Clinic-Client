package models;

import java.io.Serializable;
import play.data.validation.Constraints;

/**
 * The persistent class for the user database table.
 * 
 */

public class User implements Serializable {

	private int id;

	private String address;

	@Constraints.Required
	private String code;

	private String email;

	private String name;

	private String password;

	private String phoneNumber;

	private String status;

	private String type;
	
	public User(){
		
	}
	
	public User(int id, String code, String address, String email, 
			String name, String password, String phoneNumber, String status, String type) {
		this.id = id;
		this.code = code;
		this.email = email;
		this.name = name;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public User(String name) { this.name = name; }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        User aux = (User) obj;
            return (id == aux.id);
    }

}