package demo.model;

public class UserLogin {
	private Long id;
	private String username;
	private String token;
	

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserLogin [id=" + id + ", username=" + username + ", token=" + token + "]";
	}

	
	
}
