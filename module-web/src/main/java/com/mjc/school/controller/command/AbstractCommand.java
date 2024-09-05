package com.mjc.school.controller.command;

import com.mjc.school.controller.BaseController;

import java.util.Scanner;

public class AbstractCommand <T,R,K>{
    private final BaseController<T, R, K> controller;
    private final Scanner input;

    public BaseController<T, R, K> getController() {
        return controller;
    }

    public Scanner getInput() {
        return input;
    }

    public AbstractCommand(BaseController<T, R, K> controller, Scanner scanner) {
        this.controller = controller;
        this.input = scanner;
    }
}
