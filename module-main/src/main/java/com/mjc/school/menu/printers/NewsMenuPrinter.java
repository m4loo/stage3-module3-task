package com.mjc.school.menu.printers;

import com.mjc.school.controller.commands.NewsCommandHandler;
import com.mjc.school.controller.implementation.NewsController;
import com.mjc.school.interfaces.BaseMenuPrinter;
import com.mjc.school.menu.Buttons;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

@Component
@RequiredArgsConstructor
public class NewsMenuPrinter implements BaseMenuPrinter<NewsController> {

    private final NewsCommandHandler newsCommandHandler;

    @Override
    public void printReadAllMenu(NewsController controller) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.READ_ALL_NEWS.getButtonMessage());
        out.println(newsCommandHandler.handleCommand(controller, Buttons.READ_ALL_NEWS.name()));
    }

    @Override
    public void printReadByIdlMenu(NewsController controller, Scanner input) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.READ_BY_ID_NEWS.getButtonMessage());
        out.println(Buttons.ConstantsString.ENTER_NEWS_ID);
        String newsId = input.nextLine();
        newsCommandHandler.createRequest(newsId, null, null, null, null);
        out.println(newsCommandHandler.handleCommand(controller, Buttons.READ_BY_ID_NEWS.name()));
    }

    @Override
    public void printCreateMenu(NewsController controller, Scanner input) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.CREATE_NEWS.getButtonMessage());
        out.println(Buttons.ConstantsString.ENTER_NEWS_TITLE);
        String title = input.nextLine();
        out.println(Buttons.ConstantsString.ENTER_NEWS_CONTENT);
        String content = input.nextLine();
        out.println(Buttons.ConstantsString.ENTER_AUTHOR_ID);
        String authorId = input.nextLine();

        List<String> tagList;
        out.println(Buttons.ConstantsString.TAG_ENTER_QUESTION);
        if (input.nextLine().equals("y")) {
            out.println(Buttons.ConstantsString.TAG_ENTER_INFO);
            tagList = new ArrayList<>();
            String tagId = "";
            while (!tagId.equals("done")) {
                out.println(Buttons.ConstantsString.ENTER_TAG_ID);
                tagId = input.nextLine();
                tagList.add(tagId);
            }
        } else tagList = null;

        newsCommandHandler.createRequest(null, title, content, authorId, tagList);
        out.println(newsCommandHandler.handleCommand(controller, Buttons.CREATE_NEWS.name()));
    }

    @Override
    public void printUpdateMenu(NewsController controller, Scanner input) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.UPDATE_NEWS.getButtonMessage());
        out.println(Buttons.ConstantsString.ENTER_NEWS_ID);
        String newsId = input.nextLine();
        out.println(Buttons.ConstantsString.ENTER_NEWS_TITLE);
        String title = input.nextLine();
        out.println(Buttons.ConstantsString.ENTER_NEWS_CONTENT);
        String content = input.nextLine();
        out.println(Buttons.ConstantsString.ENTER_AUTHOR_ID);
        String authorId = input.nextLine();

        List<String> tagList;
        out.println(Buttons.ConstantsString.TAG_ENTER_QUESTION);
        if (input.nextLine().equals("y")) {
            out.println(Buttons.ConstantsString.TAG_ENTER_INFO);
            tagList = new ArrayList<>();
            String tagId = "";
            while (!tagId.equals("done")) {
                out.println(Buttons.ConstantsString.ENTER_TAG_ID);
                tagId = input.nextLine();
                tagList.add(tagId);
            }
        } else tagList = null;

        newsCommandHandler.createRequest(newsId, title, content, authorId, tagList);
        out.println(newsCommandHandler.handleCommand(controller, Buttons.UPDATE_NEWS.name()));
    }

    @Override
    public void printDeleteByIdMenu(NewsController controller, Scanner input) {
        out.println(Buttons.ConstantsString.OPERATIONS + Buttons.DELETE_BY_ID_NEWS.getButtonMessage());
        out.println(Buttons.ConstantsString.ENTER_NEWS_ID);
        newsCommandHandler.createRequest(input.nextLine(), null, null, null, null);
        out.println(newsCommandHandler.handleCommand(controller, Buttons.DELETE_BY_ID_NEWS.name()));
    }

    @Override
    public void getDefault(NewsController controller) {
        newsCommandHandler.handleCommand(controller, "DEFAULT");
    }
}
