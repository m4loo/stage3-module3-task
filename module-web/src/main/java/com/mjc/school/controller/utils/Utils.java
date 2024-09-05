package com.mjc.school.controller.utils;

import com.mjc.school.service.exception.ErrorCode;
import com.mjc.school.service.exception.InvalidDataException;

import java.util.Scanner;

public class Utils {
    private Utils() {

    }

    public static long inputLongNumber(Scanner scanner) {
        try {
            long userNumber = scanner.nextLong();
            scanner.nextLine();
            return userNumber;
        } catch (Exception e) {
            scanner.nextLine();
            throw new InvalidDataException(String.format(ErrorCode.VALIDATE_INT_VALUE.getMessage(), "args"));
        }
    }

}
