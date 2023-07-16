package org.example.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    List<Observer> listOfObservers = new ArrayList<Observer>();
    int count;
    public int getCount() {
        return this.count;
    }

    public void incrementCount() {
        this.count+=1;
        if(this.count > 5)
        {
            shouldNotify();
        }
    }
    public void setConsumer(Observer name)
    {
        this.listOfObservers.add(name);
    }

    public List<Observer> getListOfObservers()
    {
        return this.getListOfObservers();
    }

    private void shouldNotify() {
        if(getCount() > 5)
        {
            for(Observer obj : listOfObservers)
            {
                obj.update();
            }
        }
    }
}
