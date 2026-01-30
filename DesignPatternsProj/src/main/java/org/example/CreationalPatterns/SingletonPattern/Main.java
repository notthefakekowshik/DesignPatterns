package org.example.CreationalPatterns.SingletonPattern;

public class Main {

    public static void main(String[] args) {
    }

    /*
          1. Singleton Pattern -> there should only be 1 object.

          Class with more one than 1 object is no class.
            Method 1:
            Create an empty constructor and make it private, then object cant be fetched using new.
            Create a get method and and use that to return obj.
            Is there any drawback in this?
            yes, what if there >=2 threads trying to call that method. In this case, >1 objects may present.

     */

}
