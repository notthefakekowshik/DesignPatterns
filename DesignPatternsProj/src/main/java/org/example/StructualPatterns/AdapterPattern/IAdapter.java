package org.example.StructualPatterns.AdapterPattern;

public interface IAdapter {

    String convertDataToJson(String data);

    String convertDataToSoap(String data);
}
