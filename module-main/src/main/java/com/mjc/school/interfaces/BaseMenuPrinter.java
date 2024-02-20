package com.mjc.school.interfaces;

import java.util.Scanner;

public interface BaseMenuPrinter<C> {

    void printReadAllMenu(C controller);

    void printReadByIdlMenu(C controller, Scanner input);

    void printCreateMenu(C controller, Scanner input);

    void printUpdateMenu(C controller, Scanner input);

    void printDeleteByIdMenu(C controller, Scanner input);

    void getDefault(C controller);
}
