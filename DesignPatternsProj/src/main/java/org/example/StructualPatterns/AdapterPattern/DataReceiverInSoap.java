package org.example.StructualPatterns.AdapterPattern;

public class DataReceiverInSoap implements DataReceiver{

    @Override
    public String getResultData() {
        return new AdapterImpl().convertDataToSoap(new XMLDataProvider().giveData());
    }

}
