package server.exceptons;

/**
 * exception thrown when a message is incorrect
 */
public class IllegalMessageArgument extends Exception {

    public IllegalMessageArgument(){
        super();
    }

    public IllegalMessageArgument(String msg){
        super(msg);
    }
}
