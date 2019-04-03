package com.mockitoscala.debug.argumentresolver;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

@Component
public class HeaderParamsResolver implements ArgumentResolver<String> {

    private boolean isResolved;

    @Override
    public String resolve(NativeWebRequest nativeWebRequest) {
        isResolved = true;
        return  "gursahib";
//        throw new IllegalArgumentException();
//        return null;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }

    public boolean isResolved() {
        return isResolved;
    }
}
