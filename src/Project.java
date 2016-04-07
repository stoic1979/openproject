
public class Project {
	private int id;
	private String title; 
	private String description;   
	private String startDate;
	private String endDate; 
	private String owner; 

	public Project() {}
	public Project(String title, String description, String startDate, String endDate, String owner) {
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.owner = owner;
	}
	public int getId() {
		return id;
	}
	public void setId( int id ) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle( String title ) {
		this.title = title;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate( String startDate ) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate( String endDate ) {
		this.endDate = endDate;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner( String owner ) {
		this.owner = owner;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
} //Project
