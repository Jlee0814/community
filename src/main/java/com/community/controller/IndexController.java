package com.community.controller;

import com.community.dto.PaginationDTO;
import com.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;
    @GetMapping ("/")
    public String index(HttpServletRequest request
                        , Model model,
                        @RequestParam(value="page",defaultValue = "1") Integer page,
                        @RequestParam(value="size",defaultValue = "5") Integer size){

        PaginationDTO pagination=questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
