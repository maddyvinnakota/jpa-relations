package eu.olaf.example.service.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends NoSuchElementException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
