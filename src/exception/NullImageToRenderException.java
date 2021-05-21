package exception;

public class NullImageToRenderException extends Exception {
    public NullImageToRenderException() {
        super("Image animation frame is null.");
    }
}
