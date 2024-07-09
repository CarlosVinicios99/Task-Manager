package carpelune.workspaces.dto;

import java.util.UUID;

public record UpdateWorkspaceDTO(UUID id, UUID userId, String title, String description) {

}
