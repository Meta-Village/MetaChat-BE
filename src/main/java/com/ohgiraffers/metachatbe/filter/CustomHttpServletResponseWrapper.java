package com.ohgiraffers.metachatbe.filter;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.*;

class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream capture;
    private PrintWriter writer;
    private ServletOutputStream outputStream;
    private boolean writerUsed = false;
    private boolean outputStreamUsed = false;

    public CustomHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
        capture = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (writerUsed) {
            throw new IllegalStateException("getWriter() has already been called on this response.");
        }
        if (outputStream == null) {
            outputStream = new ServletOutputStream() {
                private ByteArrayOutputStream stream = capture;

                @Override
                public void write(int b) throws IOException {
                    stream.write(b);
                }

                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setWriteListener(WriteListener listener) {
                    // No implementation needed for this example
                }
            };
        }
        outputStreamUsed = true;
        return outputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (outputStreamUsed) {
            throw new IllegalStateException("getOutputStream() has already been called on this response.");
        }
        if (writer == null) {
            writer = new PrintWriter(new OutputStreamWriter(capture, getCharacterEncoding()));
        }
        writerUsed = true;
        return writer;
    }

    public String getCaptureAsString() throws IOException {
        if (writer != null) {
            writer.flush(); // Ensure all data is written to the stream
        }
        return capture.toString(getCharacterEncoding());
    }
}