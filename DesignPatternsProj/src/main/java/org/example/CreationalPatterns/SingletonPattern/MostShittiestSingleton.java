package org.example.CreationalPatterns.SingletonPattern;

class InstanceProvider {

    private static InstanceProvider instanceProvider;
    private InstanceProvider() {}

    public static InstanceProvider getInstanceProvider() {
        if(instanceProvider == null) {
            instanceProvider = new InstanceProvider();
        }
        return instanceProvider;
    }
}
public class MostShittiestSingleton {

    public static void main(String[] args) {

    }
}

// this is the problem, not thread safe.
