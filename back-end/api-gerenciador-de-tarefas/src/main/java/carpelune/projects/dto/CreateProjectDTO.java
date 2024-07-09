package carpelune.projects.dto;

import java.util.UUID;

public record CreateProjectDTO(String title, String description, UUID workspaceId) {

}
