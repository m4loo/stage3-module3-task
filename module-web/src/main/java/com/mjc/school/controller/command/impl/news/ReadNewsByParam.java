package com.mjc.school.controller.command.impl.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

import java.util.List;
import java.util.Scanner;

public class ReadNewsByParam implements Command {

    private NewsController newsController;
    private Scanner scanner;

    public ReadNewsByParam(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController, Scanner scanner) {
        this.newsController = (NewsController) newsController;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println(MenuConstants.ENTER_TAG_ID);
        List<Long> listId = List.of(Utils.inputLongNumber(scanner));
        System.out.println(MenuConstants.ENTER_TAG);
        List<String> tagsName = List.of(scanner.nextLine());
        System.out.println(MenuConstants.ENTER_AUTHOR_NAME);
        String authorName = scanner.nextLine();
        System.out.println(MenuConstants.ENTER_TITLE);
        String title = scanner.nextLine();
        System.out.println(MenuConstants.ENTER_CONTENT);
        String content = scanner.nextLine();
        System.out.println(newsController.getNewsByParam(
                listId,tagsName,authorName,title,content));;

    }
}
