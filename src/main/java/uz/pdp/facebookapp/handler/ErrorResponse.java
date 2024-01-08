package uz.pdp.facebookapp.handler;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ErrorResponse {
    private String message;
    private final LocalDateTime date = LocalDateTime.now();
}
