package carpelune.workspaces.dto;

import java.util.UUID;

public record CreateWorkspaceDTO(UUID userId, String title, String description) {

}
