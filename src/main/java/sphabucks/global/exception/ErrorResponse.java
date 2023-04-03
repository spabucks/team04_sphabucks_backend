package sphabucks.global.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private String description;

    public static ErrorResponse of(int status, String message, String description){
        return ErrorResponse.builder()
                .status(status)
                .message(message)
                .description(description)
                .build();
    }
}
