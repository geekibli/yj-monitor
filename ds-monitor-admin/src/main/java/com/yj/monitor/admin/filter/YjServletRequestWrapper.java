package com.yj.monitor.admin.filter;


import com.yj.monitor.api.util.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;


public class YjServletRequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    public YjServletRequestWrapper(HttpServletRequest request) {
        super(request);
        String string = "";
        try {

//            string = StreamUtil.read(request.getInputStream());
            string = IOUtils.toString(request.getInputStream());
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
        body = string;
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }
        };
        return servletInputStream;
    }

    public String getRequestBody() {
        return body;
    }
}

