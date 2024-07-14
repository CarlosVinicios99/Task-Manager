package carpelune.users.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import carpelune.authentication.models.Login;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@Column()
	private String name;
	
	@OneToOne
	@JoinColumn(name = "login_id", nullable = false, unique = true)
	private Login login;
	
	
	public User() {
		
	}
	
	public User(UUID id, String name, Login login) {
		this.id = id;
		this.name = name;
		this.login = login;
	}
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(id, login, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(login, other.login) && Objects.equals(name, other.name);
	}
		
}


