package ru.otus.lesson.utils;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class InputStreamScannerImpl implements InputStreamScanner {

    @Override
    public InputStream getAnswer() {
        return System.in;
    }
}
