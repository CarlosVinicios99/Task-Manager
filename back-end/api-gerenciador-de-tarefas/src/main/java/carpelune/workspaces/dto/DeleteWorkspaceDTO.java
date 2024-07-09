package carpelune.workspaces.dto;

import java.util.UUID;

public record DeleteWorkspaceDTO(UUID workspaceId, UUID userId) {

}
