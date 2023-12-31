package com.melis.todoProject.todolist.boundary.controller;

import com.melis.todoProject.todolist.control.service.ToDoListService;
import com.melis.todoProject.todolist.control.service.ToDoListServiceImpl;
import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ToDoListPostController {

    private final ToDoListService toDoListService;

    @Autowired
    public ToDoListPostController(ToDoListServiceImpl toDoListService) {
        this.toDoListService = toDoListService;
    }

    @PostMapping("/list/add")
    public String addNewList(@ModelAttribute ToDoListModel toDoList, Authentication authentication) {
        toDoListService.addNewListToLoggedUser(toDoList, authentication.getName());
        return "redirect:/list/get";
    }
}
