package exceptons;

public class IllegalMessageArgument extends Exception {

    public IllegalMessageArgument(){
        super();
    }

    public IllegalMessageArgument(String msg){
        super(msg);
    }
}
