package carpelune.users.dto;

import java.util.UUID;

public record FindUserByIdDTO(UUID id, String name, String email) {

}
