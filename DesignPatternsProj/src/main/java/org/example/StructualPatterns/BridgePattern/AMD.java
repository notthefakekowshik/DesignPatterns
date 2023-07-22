package org.example.StructualPatterns.BridgePattern;

public class AMD implements Processor{

    AMDProcessors amdProcessors;

    AMD(AMDProcessors amdProcessors)
    {
        this.amdProcessors = amdProcessors;
    }

    @Override
    public void showProcessor() {

    }

}
