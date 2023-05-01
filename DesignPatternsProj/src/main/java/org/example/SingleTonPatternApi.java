package org.example;

import implClasses.SingleTonPatternImplEagerIni;
import implClasses.SingleTonPatternImplEagerIniWithStatic;

public class SingleTonPatternApi {
        public SingleTonPatternApi() {
            //SingleTonPatternImpl currObj = new SingleTonPatternImpl(); //this leads to error because constructor is in private.
            SingleTonPatternImplEagerIni obj1 = SingleTonPatternImplEagerIni.SingleTonPatternObjFetcher();
            SingleTonPatternImplEagerIni obj2 = SingleTonPatternImplEagerIni.SingleTonPatternObjFetcher();
            System.out.println("object count is");
            System.out.println(SingleTonPatternImplEagerIni.objectCounterAsClassVariable);
            System.out.println("impl with static block of eager ini");

            SingleTonPatternImplEagerIniWithStatic obj4;
            SingleTonPatternImplEagerIniWithStatic obj3;
            System.out.println("object count is");
            System.out.println(SingleTonPatternImplEagerIniWithStatic.singleTonPatternImplEagerIniWithStaticObjectCount);
        }
}

