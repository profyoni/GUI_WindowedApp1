public class SingletonDesignPattern {
    static{
        theInstance = new SingletonDesignPattern();

    }
    private static volatile SingletonDesignPattern theInstance;

    private SingletonDesignPattern(){
        // instance uses 1TB RAM
    }

    public static SingletonDesignPattern getInstance() {
        if (theInstance == null)
        {
            synchronized (SingletonDesignPattern.class){
                if (theInstance == null)
                {
                    theInstance = new SingletonDesignPattern();
                }
            }
        }

        return theInstance;
    }
}
