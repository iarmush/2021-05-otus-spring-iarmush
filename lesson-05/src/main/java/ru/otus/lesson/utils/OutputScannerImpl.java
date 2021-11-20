package ru.otus.lesson.utils;

import java.io.InputStream;
import org.springframework.stereotype.Service;

@Service
public class OutputScannerImpl implements OutputScanner {

    @Override
    public InputStream getConsoleOutput() {
        return System.in;
    }
}
