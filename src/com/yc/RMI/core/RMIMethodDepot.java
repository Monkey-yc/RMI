package com.yc.RMI.core;

import java.util.HashMap;
import java.util.Map;

public class RMIMethodDepot {
    private final Map<String, RMIMethodDefinition> methodMap;

    RMIMethodDepot() {
        methodMap = new HashMap<>();
    }

    void addMethod(String methodId, RMIMethodDefinition rmiMethodDefinition) {
        RMIMethodDefinition rmd = methodMap.get(methodId);
        if (rmd != null) {
            return;
        }
        methodMap.put(methodId, rmiMethodDefinition);
    }

    RMIMethodDefinition getMethod(String methodId) {
        return methodMap.get(methodId);
    }
}
