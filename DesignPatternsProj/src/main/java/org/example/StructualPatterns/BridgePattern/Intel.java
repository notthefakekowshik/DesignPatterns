package org.example.StructualPatterns.BridgePattern;

public class Intel implements Processor{

    IntelProcessors intelProcessors;
    Intel(IntelProcessors intelProcessors)
    {
        this.intelProcessors = intelProcessors;
    }

    @Override
    public void showProcessor() {
        System.out.println("thsi is intel processor with the processor " + this.intelProcessors.getProcessConfig());
    }

}
