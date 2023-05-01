package implClasses;

import lombok.Data;

@Data
public class SingleTonPatternImpl {
    public static int objectCounterAsClassVariable;
    /*
        1. Make the constructor private and create instance with private access.
     */

    private static SingleTonPatternImpl SingleTonPatternImplObj;
    public static int objectCount;
    private SingleTonPatternImpl()
    {

    }
    public static SingleTonPatternImpl SingleTonPatternObjFetcher() {
        if (SingleTonPatternImplObj == null) {
            SingleTonPatternImplObj = new SingleTonPatternImpl();
            objectCounterAsClassVariable += 1;
        }
        return SingleTonPatternImplObj;
    }
}
