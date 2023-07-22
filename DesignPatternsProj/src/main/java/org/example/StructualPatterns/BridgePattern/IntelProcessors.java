package org.example.StructualPatterns.BridgePattern;

import lombok.Data;

@Data
public class IntelProcessors implements ProcessorConfig{

    String processConfig;
    @Override
    public void showProcessorConfig() {

    }

}
