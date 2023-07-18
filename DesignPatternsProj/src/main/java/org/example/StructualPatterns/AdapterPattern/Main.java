package org.example.StructualPatterns.AdapterPattern;

public class Main {

    public static void main(String[] args) {
        System.out.println(new DataReceiverInJson().getResultData());
        System.out.println(new DataReceiverInSoap().getResultData());
        

    }
}
