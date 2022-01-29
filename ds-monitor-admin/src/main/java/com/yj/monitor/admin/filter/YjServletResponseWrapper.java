package com.yj.monitor.admin.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class YjServletResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream byteArrayOutputStream;
    private ServletOutputStream servletOutputStream;

    public YjServletResponseWrapper(HttpServletResponse response) {
        super(response);
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (servletOutputStream != null) {
            return servletOutputStream;
        }
        servletOutputStream = new ServletOutputStream() {
            @Override
            public void write(int b) throws IOException {
                byteArrayOutputStream.write(b);
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
            }
        };
        return servletOutputStream;
    }

    public void flush() {
        try {
            if (servletOutputStream != null) {
                servletOutputStream.flush();
            }
            if (servletOutputStream != null) {
                servletOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public byte[] toByteArray() {
        return byteArrayOutputStream.toByteArray();
    }

    public String getResult() {
        flush();
        String body = "";
        try {
            body = new String(toByteArray(), getCharacterEncoding());
        } catch (UnsupportedEncodingException ignore) {
        }
        return body;
    }
}
