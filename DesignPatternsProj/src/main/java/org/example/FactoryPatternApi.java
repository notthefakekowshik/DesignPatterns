package org.example;

import implClasses.Axis;
import implClasses.HDFC;
import implClasses.Icici;

public class FactoryPatternApi {
    String bankName;
    FactoryPatternApi(String bankName)
    {
        this.bankName = bankName;
    }
    public Bank getBankInstance()
    {
        if(this.bankName == "HDFC")
        {
            return new HDFC();
        }
        else if(this.bankName == "AXIS")
        {
            return new Axis();
        }
        else if(this.bankName == "ICICI")
        {
            return new Icici();
        }
        else {
            return null;
        }
    }
}

/*
    It defined an interface or an abstract class for creating an object but subclasses decide which class to instantiate.
    When should I use Factory pattern?
    1. When a class doesn't know what sub classes will be required to create.
    2. When a class wants that its sub-classes specify the objects to be created.
    3. When the parent classes choose the creation of objects to its sub-classes.
    Suppose ipdu, bank ane interface undi.
    There are HDFC bank, axis, icici etc etc etc.
    Then if a particular bank implements the bank interface, then we can give an instance to the user of a particular bank when they use that.
 */
