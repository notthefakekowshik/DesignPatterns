package implClasses;

public class SingleTonPatternImplEagerIniWithStatic {
    public static SingleTonPatternImplEagerIniWithStatic singleTonPatternImplEagerIniWithStatic;
    public static int singleTonPatternImplEagerIniWithStaticObjectCount;
    private SingleTonPatternImplEagerIniWithStatic()
    {

    }
    static {
        singleTonPatternImplEagerIniWithStatic = new SingleTonPatternImplEagerIniWithStatic();
        singleTonPatternImplEagerIniWithStaticObjectCount+=1;
    }
    public void testingFunction()
    {
        System.out.println("implementing with static block");
    }
}
