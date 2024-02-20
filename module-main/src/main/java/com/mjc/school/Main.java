package com.mjc.school;

import com.mjc.school.configuration.ProgramConfiguration;
import com.mjc.school.menu.MenuHelper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(ProgramConfiguration.class)) {
            MenuHelper menuHelper = context.getBean(MenuHelper.class);
            menuHelper.printMainMenu();
        }
    }
}