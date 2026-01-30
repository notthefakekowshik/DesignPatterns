package org.example.CreationalPatterns.SingletonPattern;

class BillPughSingletonDemo {

    private BillPughSingletonDemo() {
        System.out.println("BillPughSingleton instance created.");
    }

    // A static inner class that is not loaded until it's referenced.
    private static class SingletonHolder {
        private static final BillPughSingletonDemo billInstance = new BillPughSingletonDemo();
    }

    public static BillPughSingletonDemo billPughSingletonInstance() {
        return SingletonHolder.billInstance;
    }
}

class BillPughSingleton {
    public static void main(String[] args) {
        BillPughSingletonDemo billPughSingletonDemo = BillPughSingletonDemo.billPughSingletonInstance();
        System.out.println(billPughSingletonDemo);
    }
}
