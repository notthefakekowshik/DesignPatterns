package org.example;

import implClasses.SingleTonPatternImpl;

public class SingleTonPatternApi {
        public SingleTonPatternApi() {
            //SingleTonPatternImpl currObj = new SingleTonPatternImpl(); //this leads to error because constructor is in private.
            SingleTonPatternImpl obj1 = SingleTonPatternImpl.SingleTonPatternObjFetcher();
            SingleTonPatternImpl obj2 = SingleTonPatternImpl.SingleTonPatternObjFetcher();
            System.out.println("object count is");
            System.out.println(SingleTonPatternImpl.objectCounterAsClassVariable);
        }
}

