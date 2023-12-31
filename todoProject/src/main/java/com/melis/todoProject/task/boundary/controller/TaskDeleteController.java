package com.melis.todoProject.task.boundary.controller;

import com.melis.todoProject.exception.customExceptions.TaskNotFoundException;
import com.melis.todoProject.exception.customExceptions.UserNotOwnerException;
import com.melis.todoProject.locking.LockTemplate;
import com.melis.todoProject.task.control.service.TaskService;
import com.melis.todoProject.task.control.service.TaskServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TaskDeleteController {
    private final TaskService taskService;
    private final LockTemplate lockTemplate;

    @Autowired
    public TaskDeleteController(LockTemplate lockTemplate, TaskServiceImp taskService) {
        this.taskService = taskService;
        this.lockTemplate = lockTemplate;
    }

    @DeleteMapping("task/delete/{id}")
    public String deleteTask(@PathVariable Integer id, Authentication authentication) {
        if (taskService.checkTaskNotExists(id)) throw new TaskNotFoundException("Task not found");
        if (taskService.checkTaskAuthorisation(authentication.getName(), id))
            throw new UserNotOwnerException("You must be owner to delete task");
        lockTemplate.setLock(id, () -> taskService.deleteTask(id, authentication.getName())
        );
        return "redirect:/task/unfinished";
    }
}
