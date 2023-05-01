package implClasses;

import lombok.Data;

@Data
public class SingleTonPatternImplEagerIni {
    public static int objectCounterAsClassVariable;
    /*
        1. Make the constructor private and create instance with private access.
     */

    private static SingleTonPatternImplEagerIni SingleTonPatternImplObj;
    public static int objectCount;
    private SingleTonPatternImplEagerIni()
    {

    }
    public static SingleTonPatternImplEagerIni SingleTonPatternObjFetcher() {
        if (SingleTonPatternImplObj == null) {
            SingleTonPatternImplObj = new SingleTonPatternImplEagerIni();
            objectCounterAsClassVariable += 1;
        }
        return SingleTonPatternImplObj;
    }
}
