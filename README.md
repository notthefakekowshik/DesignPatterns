DesignPatterns are mainly classied into three types : 
1. Creational pattern
2. Structural pattern
3. Behavioral pattern

Creational pattern : 
  In these patterns, the main concentration will be on creating objects without using the new keyword.
  1. Factory pattern
     Define an interface or abstract class for creating an object but let the subclasses decide which class to instantiate.
      in a simpler way => subclasses are responsible to create the instance of the class.
  2. Singleton pattern : 
     Only one object should be created.
     Eager instantiation
     Lazy instantiation
  3. Abstract pattern : 
      Lets a class returns a factory of classes.
      One step higher than the factory pattern.
  4. Prototype pattern : 
     Instead of using the original object, use a prototype object and have fun with it.
     Cloning an original object and do our customization on the cloned object.
  5. Builder pattern : 
        Construct a complex object from simple objects using step by step approach.

Structural pattern : 
    1. Composite pattern :  (Leaf-Component)
        Allow clients to operate in generic manner on objects that may or may not represent a hierarchy of objs.
        it is used when we want to represent full or partial hierarchy of objects.
        When the responsibilities are needed to be added dynamically to the individual objects without affecting other objects. Where the responsibility of object may vary from time to time.
    2. Adapter pattern : 
        provide the interface according to client requirement while using the services of a class with a different service.
        it allows two or more incompatible objects to interact.
    3. Decorator pattern :
        1. Allows us to add new behaviour or responsibilities to individual objects without affecting the behaviour of other objects in the same class.
        2. Allows us to add new functionality to an existing object without altering its structure.
        It involves creating a set of decorator classes that are used to wrap concrete components, enhancing their behavior.
        Eg: Plain Dosa to MasalaDosa/OnionDosa etc...
        Eg : Shape interface, circle and rect will implement it and a shape decorator 
        Eg:  Text,Italic Text, cpaital, bold etc etc..
    4. Bridge pattern : 
        Allows us to separate the abstraction from implementation.
    5. Facade pattern :
        Provide a simplified interface to a complex system.
        Xomato is the best example, client need not book a cab, look for a delivery agent, etc..
        Just let xomato know about this and they will do this for you.
    

Behavioral pattern : 
    1. Strategy pattern : 
        A class behavior or its algorithm can be changed at run time.
        Eg : Payment strategy,credit or debit etc.
    2. Observer pattern :
        Used when there is one-to-many relationships between objects such as if one object is modified, the independent objects are to be notified automatically.
        It uses three actor classes.
        1. Subject.
        2. Observer
        3. Client (Consumer may be?)
        Subject will maintain a list of observers and notifies when the state of the subject changes.
        Subject will either detach/attach the observer.


Must known design patterns : 
1. Factory
2. Abstract Factory
3. Singleton
4. Observer
5. Builder
6. Decorator
7. Adapter
8. Strategy
9. Facade

