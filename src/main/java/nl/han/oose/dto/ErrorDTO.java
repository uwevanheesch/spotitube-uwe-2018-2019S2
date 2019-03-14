package nl.han.oose.dto;

public class ErrorDTO {

    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }

    public ErrorDTO() {
    }

    public String getMessage() {
        return message;
    }
}
