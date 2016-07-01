package models;

public class ClinicMessage {
	private String clientId;
	private String subject;
	private String content;
	public ClinicMessage(String id, String content){
		this.subject = "";
		this.clientId = id;
		this.content = content;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
