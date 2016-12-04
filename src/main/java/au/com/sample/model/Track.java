package au.com.sample.model;

public class Track {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Track withName(String name) {
		this.name = name;
		return this;
	}
	
}
