package com.mjc.school.controller.command.impl.tag;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.constants.MenuConstants;
import com.mjc.school.controller.impl.TagController;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

import java.util.Scanner;

public class ReadTagsByNewsId implements Command {

   private  TagController tagController;
   private Scanner scanner;

    public ReadTagsByNewsId(BaseController<TagDtoRequest, TagDtoResponse, Long> tagController, Scanner scanner) {
        this.tagController = (TagController) tagController;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println(MenuConstants.ENTER_NEWS_ID);
        System.out.println(tagController.getTagsByNewsId(Utils.inputLongNumber(scanner)));

    }
}
