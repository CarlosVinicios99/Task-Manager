package carpelune.users.dto;

import java.util.UUID;

public record FindUserDTO(UUID id, String name, String email) {

}
