package carpelune.workspaces.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "workspaces")
public class Workspace implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@Column()
	private String title;
	
	@Column()
	private String description;
	
	@GeneratedValue
	@Column(name = "creation_timestamp")
	private Long creationTimestamp;
	
	
	public Workspace() {
		
	}
	
	public Workspace(UUID id, String title, String description, Long creationTimestamp) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.creationTimestamp = creationTimestamp;
	}
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Long creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(creationTimestamp, description, id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Workspace other = (Workspace) obj;
		return Objects.equals(creationTimestamp, other.creationTimestamp)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(title, other.title);
	}

}
