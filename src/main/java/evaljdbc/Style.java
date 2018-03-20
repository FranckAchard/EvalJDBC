package evaljdbc;

public class Style {
	private Long id;
	private String name;
	
	// constructors
	public Style() {
		id= null;
		name= "";
	}
	
	public Style(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Style(Style style) {
		this.id = style.id;
		this.name = style.name;		
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
		return "Style [id=" + id + ", name=" + name + "]";
	}
	
	

}
