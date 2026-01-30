package org.example.StructualPatterns.AdapterPattern;

public class DataReceiverInJson implements DataReceiver{

    @Override
    public String getResultData(){
        return new AdapterImpl().convertDataToJson(new XMLDataProvider().giveData());
    }


}
