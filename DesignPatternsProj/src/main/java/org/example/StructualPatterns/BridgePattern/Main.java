package org.example.StructualPatterns.BridgePattern;

public class Main {

    public static void main(String[] args) {
        Intel inteli7 = new Intel(new IntelProcessors());
        inteli7.intelProcessors.setProcessConfig("i7");
        inteli7.showProcessor();

        Intel inteli5 = new Intel(new IntelProcessors());
        inteli5.intelProcessors.setProcessConfig("i5");
        inteli5.showProcessor();
    }
}
/*

Separates an abstraction from its implementation so that the two can vary independently.
this will let you split a large class and set of classes which are closely related into hierarchies.
for example, assume that there is an interface called processor and there are classes which will implement this. They are
1. Intel i9
2. Intel i7
3. Intel i5
4. Intel i3

5. AMD R7
6. AMD R5
etc etc

in this case why should we create all these classes? we will create an hierarchy.
two of them are Intel, AMD on one side
and on the other side intel processors and AMD processors.

 */
