package org.example.StructualPatterns.AdapterPattern;

public class AdapterImpl implements IAdapter{

    @Override
    public String convertDataToJson(String data) {
        return data + "converted to JSON!";
    }

    @Override
    public String convertDataToSoap(String data) {
        return data + "converted to SOAP!";
    }

}
