package carpelune.users.models;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_workspaces")
public class UserWorkspace {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@Column()
	private UUID userId;
	
	@Column()
	private UUID workspaceId;
	
	@Column()
	private String role;
	
	
	public UserWorkspace() {
		
	}
	
	public UserWorkspace(UUID id, UUID userId, UUID workspaceId, String role) {
		this.id = id;
		this.userId = userId;
		this.workspaceId = workspaceId;
		this.role = role;
	}
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UUID getWorkspaceId() {
		return workspaceId;
	}

	public void setWorkspaceId(UUID workspaceId) {
		this.workspaceId = workspaceId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, role, userId, workspaceId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserWorkspace other = (UserWorkspace) obj;
		return Objects.equals(id, other.id) && Objects.equals(role, other.role) && Objects.equals(userId, other.userId)
			&& Objects.equals(workspaceId, other.workspaceId);
	}
		
}
