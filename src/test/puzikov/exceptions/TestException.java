package test.puzikov.exceptions;

public class TestException extends RuntimeException {
    public TestException (String message) {
        super(message);
    }

    public TestException (String message, Throwable e) {
        super(message, e);
    }
}