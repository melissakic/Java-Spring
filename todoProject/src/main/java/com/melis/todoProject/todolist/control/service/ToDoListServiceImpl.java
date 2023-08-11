package com.melis.todoProject.todolist.control.service;

import com.melis.todoProject.task.entity.model.TaskModel;
import com.melis.todoProject.todolist.control.repository.ToDoListRepository;
import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import com.melis.todoProject.user.control.service.UserService;
import com.melis.todoProject.user.control.service.UserServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoListServiceImpl implements ToDoListService {

    private final ToDoListRepository toDoListRepository;
    private final UserService userService;

    @Autowired
    public ToDoListServiceImpl(ToDoListRepository toDoListRepository, UserServiceImp userService) {
        this.toDoListRepository = toDoListRepository;
        this.userService = userService;
    }

    @Override
    public void addNewListToLoggedUser(ToDoListModel toDoListModel, String username) {
        toDoListRepository.save(toDoListModel);
        userService.addListToUser(toDoListModel, username);
    }

    @Transactional
    @Override
    public List<String> getListNamesFromUser(String username) {
        List<String> list = new ArrayList<>();
        List<ToDoListModel> retrievedLists = userService.getUser(username).getToDoLists();
        retrievedLists.forEach(item -> {
            list.add(item.getListName());
        });
        return list;
    }

    @Transactional
    @Override
    public ToDoListModel getListByName(String name) {
        return toDoListRepository.findByListName(name);
    }

    @Transactional
    @Override
    public List<ToDoListModel> getListFromUser(String username) {
        List<ToDoListModel> lista = new ArrayList<>(userService.getUser(username).getToDoLists());
        return lista;
    }

    @Transactional
    @Override
    public ToDoListModel getListById(Integer id) {
        Optional<ToDoListModel> fetced = toDoListRepository.findById(id);
        ToDoListModel data = new ToDoListModel();
        for (TaskModel task : fetced.get().getTask()) {
            data.getTask().add(task);
        }
        return data;
    }
}
