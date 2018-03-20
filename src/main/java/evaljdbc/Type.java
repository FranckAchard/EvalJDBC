package evaljdbc;

public class Type {
	private Long id;
	private String name;
	
	// constructors
	public Type() {
		id= null;
		name= "";
	}
	
	public Type(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Type(Type type) {
		this.id = type.id;
		this.name = type.name;		
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
		return "Type [id=" + id + ", name=" + name + "]";
	}
	
	

}
