package evaljdbc;

public class Disc {
	// attributes
	
	private Long id;
	private String title;
	private Long artistId;
	private String artistName;
	private Long typeId;
	private String typeName;
	private Long styleId;
	private String styleName;
	private int year;
	private double rating;


	// constructors
	
	public Disc() {
		this.id= null;
		this.title= "";
		this.artistId= null;
		this.artistName= "";
		this.typeId= null;
		this.typeName= "";
		this.styleId= null;
		this.styleName= "";
		this.year= 0;
		this.rating= 0.0;
	}
		
	public Disc(Long id, String title, Long artistId, String artistName, Long typeId, String typeName, Long styleId,
			String styleName, int year, double rating) {
		this.id= id;
		this.title= title;
		this.artistId= artistId;
		this.artistName= artistName;
		this.typeId= typeId;
		this.typeName= typeName;
		this.styleId= styleId;
		this.styleName= styleName;
		this.year= year;
		this.rating= rating;
	}

	public Disc(Disc disc) {
		this.id= disc.id;
		this.title= disc.title;
		this.artistId= disc.artistId;
		this.artistName= disc.artistName;
		this.typeId= disc.typeId;
		this.typeName= disc.typeName;
		this.styleId= disc.styleId;
		this.styleName= disc.styleName;
		this.year= disc.year;
		this.rating= disc.rating;
	}

	// getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getArtistId() {
		return artistId;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getStyleId() {
		return styleId;
	}

	public void setStyleId(Long styleId) {
		this.styleId = styleId;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Disc [id = " + id + " ; title = " + title + " ; artistId = " + artistId + " ; artistName = " + artistName
				+ " ; typeId = " + typeId + " ; typeName = " + typeName + " ; styleId = " + styleId + " ; styleName = " + styleName
				+ " ; year = " + year + " ; rating = " + rating + "]";
	}
	
}
