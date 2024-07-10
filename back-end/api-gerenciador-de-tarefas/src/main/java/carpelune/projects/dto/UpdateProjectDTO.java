package carpelune.projects.dto;

import java.util.UUID;

public record UpdateProjectDTO(UUID id, UUID userId, String title, String description) {

}
