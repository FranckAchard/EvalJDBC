package evaljdbc;

public class Artist {
	// attributes
	private Long id;
	private String name;
	
	// constructors
	public Artist() {
		this.id = null;
		this.name = "";
	}
	
	public Artist(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Artist(Artist artist) {
		this.name = artist.name;
	}
	
	
	// getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + "]";
	}
	

}
