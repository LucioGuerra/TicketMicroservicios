package com.tickets.comment_sv.anotation;

import com.tickets.comment_sv.exception.TicketException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MaxFileListValidator implements ConstraintValidator<MaxFileListSize, List<MultipartFile>> {

    private int max;

    @Override
    public void initialize(MaxFileListSize constraintAnnotation) {
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(List<MultipartFile> files, ConstraintValidatorContext context) {
        if (files != null && files.size() > max) {
            throw new TicketException("TOO_MANY_FILES", "Max file list size is 5");
        }
        return true;
    }
}