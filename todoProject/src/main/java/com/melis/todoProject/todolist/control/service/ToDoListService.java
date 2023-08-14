package com.melis.todoProject.todolist.control.service;

import com.melis.todoProject.todolist.entity.model.ToDoListModel;

import java.util.List;

public interface ToDoListService {
    void addNewListToLoggedUser(ToDoListModel toDoListModel, String username);

    List<String> getListNamesFromUser(String username);

    List<ToDoListModel> getListFromUser(String username);

    void addListToUser(ToDoListModel toDoListModel, String username);

    ToDoListModel getListByName(String name);

    ToDoListModel getListById(Integer id);

    boolean checkToDoListAuthorisation(String username, Integer listId);

    void deleteList(Integer listId, String username);
}
