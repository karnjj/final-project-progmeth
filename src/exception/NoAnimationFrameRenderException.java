package exception;

public class NoAnimationFrameRenderException extends Exception {
    public NoAnimationFrameRenderException() {
        super("Animation frame is null.");
    }
}
