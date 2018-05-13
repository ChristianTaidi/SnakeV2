package server.exceptons;

/**
 * exception thrown when the snake hits itself
 */
public class AutoHitException extends Exception {

    public AutoHitException(){
        super();
    }

    public AutoHitException(String s) {
        super(s);
    }
}
