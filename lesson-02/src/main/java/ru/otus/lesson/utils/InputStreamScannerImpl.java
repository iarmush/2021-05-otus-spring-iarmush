package ru.otus.lesson.utils;

import java.io.InputStream;
import org.springframework.stereotype.Service;

@Service
public class InputStreamScannerImpl implements InputStreamScanner {

    @Override
    public InputStream getAnswer() {
        return System.in;
    }
}
