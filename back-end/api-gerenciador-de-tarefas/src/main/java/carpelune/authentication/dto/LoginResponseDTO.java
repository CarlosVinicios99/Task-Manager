package carpelune.authentication.dto;

import java.util.UUID;

public record LoginResponseDTO(String token, UUID userId) {
 
}
