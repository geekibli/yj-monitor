package com.yj.monitor.admin.filter;

import cn.hutool.http.ContentType;
import com.google.common.collect.Maps;
import com.yj.monitor.admin.config.GlobalFilterConfigurer;
import com.yj.monitor.api.util.JsonUtil;
import org.apache.catalina.connector.RequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * @Author luhaijun
 * @Description GlobalFilter
 * @Date 2019/11/28 7:52 PM
 **/
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*", filterName = "globalFilter")
public class GlobalFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(GlobalFilter.class);

    @Value("${app.config.trace.switch:true}")
    private boolean traceSwitch;
    @Resource
    private GlobalFilterConfigurer globalFilterConfigurer;

    private static final String CONTENT_TYPE = HttpHeaders.CONTENT_TYPE;
    private static final String MULTIPART_FORM_DATA = ContentType.MULTIPART.getValue();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        StopWatch stopWatch = new StopWatch("Service Request cost");
        stopWatch.start("execute");
        String resource = ((RequestFacade) request).getMethod();
        StringBuffer url = ((RequestFacade) request).getRequestURL();
        List<String> ignorePaths = globalFilterConfigurer.getIgnorePaths();
        String path = ((RequestFacade) request).getServletPath();
        boolean traceSwitch = getTraceSwitch(path);
        if (traceSwitch) {
            logger.info("Service Request input, resource: {} {}, queryString: {}", resource, url, ((RequestFacade) request).getQueryString());
            logger.info("Service Request input, headers: {}", JsonUtil.toJson(getHeaderMap((RequestFacade) request)));
        }
        String contentType = ((RequestFacade) request).getHeader(CONTENT_TYPE);
        //如果请求是上传文件，则不做处理
        if ((contentType != null && contentType.startsWith(MULTIPART_FORM_DATA))
                || (!CollectionUtils.isEmpty(ignorePaths) && ignorePaths.contains(path))) {
            filterChain.doFilter(request, response);
        } else {
            YjServletRequestWrapper yjServletRequestWrapper = new YjServletRequestWrapper((HttpServletRequest) request);
            YjServletResponseWrapper yjServletResponseWrapper = new YjServletResponseWrapper((HttpServletResponse) response);
            if (traceSwitch) {
                logger.info("Service Request input, requestBody: {}", yjServletRequestWrapper.getRequestBody());
            }
            filterChain.doFilter(yjServletRequestWrapper, yjServletResponseWrapper);
            String result = yjServletResponseWrapper.getResult();
            if (traceSwitch) {
                logger.info("Service Response output, result: {}", result);
            }
            response.getOutputStream().write(result.getBytes());
        }
        stopWatch.stop();
        if (traceSwitch) {
            logger.info(stopWatch.shortSummary());
        }
    }

    private Boolean getTraceSwitch(String path) {
        List<String> ignorePaths = globalFilterConfigurer.getIgnorePaths();
        if (!CollectionUtils.isEmpty(ignorePaths) && ignorePaths.contains(path)) {
            return false;
        }
        return traceSwitch;
    }

    @Override
    public void destroy() {
    }

    private Map<String, Object> getHeaderMap(HttpServletRequest request) {
        Map<String, Object> headerMap = Maps.newHashMap();
        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String key = headers.nextElement().toString();
            headerMap.put(key, request.getHeader(key));
        }
        return headerMap;
    }

}
