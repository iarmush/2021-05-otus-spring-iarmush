package ru.otus.lesson.utils;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class OutputScannerImpl implements OutputScanner {
    @Override
    public InputStream getConsoleOutput() {
        return System.in;
    }
}
