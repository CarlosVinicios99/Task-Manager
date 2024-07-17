package carpelune.users.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "view_users_workspaces")
public class UserWorkspaceView implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_workspace_id")
	private UUID userWorkspaceId;
	
	@Column(name = "user_id")
	private UUID userId;
	
    @Column(name = "user_name")
    private String userName;
    
    @Column()
    private String email;
    
    @Column(name = "workspace_id")
    private UUID workspaceId;
    
    @Column()
    private String title;
    
    @Column(name = "cretion_timestamp")
    private Long creationTimestamp;
    
    @Column()
    private String description;
    
    @Column(name = "user_role")
    private String userRole;
    
    
    public UserWorkspaceView(){}
    
    public UserWorkspaceView(
    	UUID userId, String userName, String email, UUID workspaceId, 
    	String title, Long creationTimestamp, String description, String userRole
    ){}
    

	public UUID getUserWorkspaceId() {
		return userWorkspaceId;
	}

	public void setUserWorkspaceId(UUID userWorkspaceId) {
		this.userWorkspaceId = userWorkspaceId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UUID getWorkspaceId() {
		return workspaceId;
	}

	public void setWorkspaceId(UUID workspaceId) {
		this.workspaceId = workspaceId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Long creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(creationTimestamp, description, email, title, userId, userName, userRole, userWorkspaceId,
				workspaceId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserWorkspaceView other = (UserWorkspaceView) obj;
		return Objects.equals(creationTimestamp, other.creationTimestamp)
			&& Objects.equals(description, other.description) && Objects.equals(email, other.email)
			&& Objects.equals(title, other.title) && Objects.equals(userId, other.userId)
			&& Objects.equals(userName, other.userName) && Objects.equals(userRole, other.userRole)
			&& Objects.equals(userWorkspaceId, other.userWorkspaceId)
			&& Objects.equals(workspaceId, other.workspaceId);
	}    
}

