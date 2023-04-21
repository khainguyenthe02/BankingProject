import Interface.*;

public class Main {
    //private static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        ICheckFunction checkFunction = new CheckFunction() ;
        checkFunction.CheckMainFunction();
    }
}