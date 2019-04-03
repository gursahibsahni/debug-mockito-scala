package com.mockitoscala.debug.argumentresolver;

import org.springframework.web.context.request.NativeWebRequest;

@FunctionalInterface
public interface ArgumentResolver<T> {
    T resolve(NativeWebRequest nativeWebRequest);
}
