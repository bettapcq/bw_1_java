package team5.exceptions;

public class AlreadyEndorsedTicket extends RuntimeException {
    public AlreadyEndorsedTicket(String message) {
        super(message);
    }
}
