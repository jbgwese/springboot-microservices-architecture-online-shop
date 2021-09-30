package com.jbgwese.productservice.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbgwese.productservice.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

@Slf4j
@Component
public class RequestResponseLoggers implements Filter {
    //for masking
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MyCustomRequestWrapper myCustomRequestWrapper = new MyCustomRequestWrapper((HttpServletRequest) servletRequest);
        String uri = myCustomRequestWrapper.getRequestURI();
        log.info("Request URI : {}", uri);
        log.info("Request Method : {}", myCustomRequestWrapper.getMethod());
        String requestData = new String(myCustomRequestWrapper.getByteArray());
        if ("/products/".equalsIgnoreCase(uri)) {
            Product product = objectMapper.readValue(requestData, Product.class);
            product.setCurrency("******");
            requestData = objectMapper.writeValueAsString(product);
        }

        log.info("Request Body : {}", requestData.replaceAll("\n", ""));

        MyCustomResponseWrapper myCustomResponseWrapper = new MyCustomResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(myCustomRequestWrapper, myCustomResponseWrapper);
        log.info("Response status - {}", myCustomResponseWrapper.getStatus());
        String responseResult = new String(myCustomResponseWrapper.getByteArrayOutputStream().toByteArray());
        log.info("Response body - {}", responseResult);
    }

    private class MyCustomRequestWrapper extends HttpServletRequestWrapper {
        private byte[] byteArray;

        public MyCustomRequestWrapper(HttpServletRequest request) {
            super(request);
            try {
                byteArray = IOUtils.toByteArray(request.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException("error encountered reading request stream");
            }
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return new MyDelegatingServletInputStream(new ByteArrayInputStream(byteArray));

        }

        public byte[] getByteArray() {
            return byteArray;
        }
    }


    private class MyCustomResponseWrapper extends HttpServletResponseWrapper {
        private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);

        public ByteArrayOutputStream getByteArrayOutputStream() {
            return byteArrayOutputStream;
        }

        public MyCustomResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new MyDelegateServletOutputStream(new TeeOutputStream(super.getOutputStream(), printStream));

        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(new TeeOutputStream(super.getOutputStream(), printStream));

        }

    }
}
