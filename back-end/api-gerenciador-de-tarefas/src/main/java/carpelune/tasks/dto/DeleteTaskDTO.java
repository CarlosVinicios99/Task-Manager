package carpelune.tasks.dto;

import java.util.UUID;

public record DeleteTaskDTO(UUID id, UUID creatorId) {

}
