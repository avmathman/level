package com.isolation.level.presentation.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Aspect
@Component
@Slf4j
public class RestLoggingAspect {

    public static final String HTTP_REQUEST_ATTRIBUTE_ID = "level-service-http-id";

    @Pointcut(value = "within(org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter) && " +
            "execution(* handleInternal(..)) && " +
            "args(request,response,handler)")
    public void handleRequest(HttpServletRequest request, HttpServletResponse response, Object handler) { }

    @Around(value = "handleRequest(request,response,handler)", argNames = "joinPoint,request,response,handler")
    public Object timeRequestHandler(ProceedingJoinPoint joinPoint, HttpServletRequest request,
                                     HttpServletResponse response, Object handler) throws Throwable {

        Object retValue;
        final Map<String, String> tags = getTags(request);
        final String requestId = "REST API call: " + tagsToString(tags);
        request.setAttribute(HTTP_REQUEST_ATTRIBUTE_ID, requestId); // Provide handler thread with the request ID.
        long start = System.nanoTime();
        try {
            log.info("Invoked {}", requestId);
            retValue = joinPoint.proceed();
            log.info("Finished {} {}", requestId, getElapsedTimeForLogs(start));
        } catch (Throwable e) {
            log.error("{} {}, error: {}", requestId, getElapsedTimeForLogs(start), e.getMessage(), e);
            throw e;
        }

        return retValue;
    }

    private String tagsToString(Map<String, String> tags) {
        return tags.entrySet().stream()
                .map(tag -> tag.getKey() + "=" + tag.getValue())
                .collect(Collectors.joining(":"));
    }

    private String getElapsedTimeForLogs(long start) {
        return String.format("elapsed time: %d ms", TimeUnit.MILLISECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS));
    }

    private Map<String, String> getTags(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String httpMethod = request.getMethod();
        String queryString = request.getQueryString();

        if (queryString != null) {
            requestUri = requestUri + "?" + queryString;
        }

        Map<String, String> tags = new ConcurrentHashMap<>();
        tags.put("path", requestUri);
        tags.put("method", httpMethod);

        return tags;
    }
}
