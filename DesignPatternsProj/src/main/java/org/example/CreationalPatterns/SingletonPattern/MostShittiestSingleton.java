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
        InstanceProvider instanceProvider = InstanceProvider.getInstanceProvider();
        System.out.println(instanceProvider.hashCode());
        InstanceProvider instanceProvider2 = InstanceProvider.getInstanceProvider();
        System.out.println(instanceProvider2.hashCode());

    }
}

// this is the problem, not thread safe.
