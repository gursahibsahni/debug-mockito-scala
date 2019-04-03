package com.mockitoscala.debug.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public final class IRSArgumentResolver implements HandlerMethodArgumentResolver {

    private HeaderParamsResolver headerParamsResolver;

    public IRSArgumentResolver(HeaderParamsResolver headerParamsResolver) {
        this.headerParamsResolver = headerParamsResolver;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return checkParameterSupport(parameter.getParameterType(), String.class, Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        String sample = "";
        if (!headerParamsResolver.isResolved())
            sample = headerParamsResolver.resolve(webRequest);

        System.out.println(sample);
        return webRequest.getParameter(parameter.getParameter().getName());
    }

    private boolean checkParameterSupport(Class parameterTypeClass, Class... clazz) {
        for (Class claz : clazz) {
            if (claz.equals(parameterTypeClass))
                return true;
        }

        return false;
    }
}