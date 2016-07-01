package models;

import java.io.Serializable;
import java.lang.reflect.Field;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import Com.ViewsMode;
import play.data.validation.Constraints;
import play.libs.Json;

/**
 * The persistent class for the user database table.
 * 
 */

public class User implements Serializable, IClinicModel {

	private int id;
	@JsonView(ViewsMode.OnlyView.class)
	private String address;
	@JsonView(ViewsMode.Delete.class)
	private String code;
	@JsonView(ViewsMode.OnlyView.class)	
	private String email;
	@JsonView(ViewsMode.OnlyView.class)
	private String name;
	@JsonView(ViewsMode.Create.class)
	private String password;
	@JsonView(ViewsMode.OnlyView.class)
	private String phoneNumber;
	@JsonView(ViewsMode.OnlyView.class)
	private String status;
	@JsonView(ViewsMode.OnlyView.class)
	private String type;
	private String authToken;
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public User(){}
	public User(int id, String code, String name, String email, String address, String password, String phoneNumber,
			String status, String type) {
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

	public User(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		User aux = (User) obj;
		return (id == aux.id);
	}

	@Override
	public JsonNode getView() {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writerWithView(ViewsMode.OnlyView.class).writeValueAsString(this);
			return Json.parse(mapper.writerWithView(ViewsMode.OnlyView.class).writeValueAsString(this));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public JsonNode getCreate() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writerWithView(ViewsMode.OnlyView.class).writeValueAsString(this);
			return Json.parse(mapper.writerWithView(ViewsMode.Create.class).writeValueAsString(this));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public JsonNode getDelete() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writerWithView(ViewsMode.OnlyView.class).writeValueAsString(this);
			return Json.parse(mapper.writerWithView(ViewsMode.Delete.class).writeValueAsString(this));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public JsonNode getEdit() {
		return this.getCreate();
	}

}