package au.com.sample.model;

public class Artist {

	private String id;
	private String name;
	private String imageURL;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public Artist withName(String name) {
		this.name = name;
		return this;
	}
	
	public Artist withImageURL(String imageURL) {
		this.imageURL = imageURL;
		return this;
	}
	
	public Artist withId(String id) {
		this.id = id;
		return this;
	}
}
