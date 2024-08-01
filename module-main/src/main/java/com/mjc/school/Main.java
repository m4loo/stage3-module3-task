package com.mjc.school;

//import com.mjc.school.configuration.ProgramConfiguration;
import com.mjc.school.menu.MenuHelper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext("com.mjc.school")) {
            context
                    .getBean(MenuHelper.class)
                    .printMainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}