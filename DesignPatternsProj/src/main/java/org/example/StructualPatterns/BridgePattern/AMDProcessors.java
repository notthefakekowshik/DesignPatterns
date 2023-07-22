package org.example.StructualPatterns.BridgePattern;

import lombok.Data;

@Data
public class AMDProcessors implements ProcessorConfig{

    String processorConfig;
    @Override
    public void showProcessorConfig() {

    }

}
