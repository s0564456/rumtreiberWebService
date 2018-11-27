package de.htw.ai.wad.bean;


public class User {
	private Integer id;
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private String role;
	
	public User(Integer id, String userId, String password, String firstName, String lastName, String role) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
	
    public User() {
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer newId) {
        this.id = newId;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userId=" + userId + ", password=" + password + ", firstName=" + firstName
                + ", lastName=" + lastName + ", role=" + role + "]";
    }
}
