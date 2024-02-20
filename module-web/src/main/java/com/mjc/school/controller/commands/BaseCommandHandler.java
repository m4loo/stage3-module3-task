package com.mjc.school.controller.commands;

import java.util.List;

public interface BaseCommandHandler<C, R> {

    String handleCommand(C controller, String buttonName);

    String toString(R respond);

    String toString(List<R> respondList);
}
