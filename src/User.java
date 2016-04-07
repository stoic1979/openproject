
public class User {
	private int id;
	private String firstName; 
	private String lastName; 
	private String username;
	private String password;
	private String email; 
	private boolean isActive;  

	public User() {}
	
	public User(String fname, String lname,  String username, String password, String email, boolean isActv) {
		this.firstName = fname;
		this.lastName = lname;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId( int id ) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}
	public void setUsername( String Username ) {
		this.username = Username;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}
	public boolean getIsActive() {
		return this.isActive;
	}
	public void setIsActive( boolean isActive ) {
		this.isActive = isActive;
	}
	
} //User
