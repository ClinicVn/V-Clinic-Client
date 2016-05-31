package models;

public class Menu {
	
	private String id;
	private String title;
	private String url;
	private String img;
	
	public Menu(){}
	public Menu(String id, String title, String url, String img){
		this.id = id;
		this.title = title;
		this.url = url;
		this.img = img;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
