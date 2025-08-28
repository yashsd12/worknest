package com.worknest.controller;

import com.worknest.model.*;
import com.worknest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private UserService userService;
    @Autowired private TaskService taskService;

    private boolean isAdmin(HttpSession s){
        Object u = s.getAttribute("user");
        return (u instanceof User) && "ADMIN".equalsIgnoreCase(((User)u).getRole());
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession s){
        if(!isAdmin(s)) return "redirect:/login";
        model.addAttribute("pending",     taskService.count(Status.PENDING));
        model.addAttribute("inprogress",  taskService.count(Status.IN_PROGRESS));
        model.addAttribute("completed",   taskService.count(Status.COMPLETED));
        model.addAttribute("delayed",     taskService.count(Status.DELAYED));
        return "admin-dashboard";
    }

    // --- Users CRUD ---
    @GetMapping("/users")
    public String users(Model model, HttpSession s){
        if(!isAdmin(s)) return "redirect:/login";
        model.addAttribute("users", userService.all());
        return "users-list";
    }

    @GetMapping("/users/new")
    public String newUserPage(HttpSession s){ if(!isAdmin(s)) return "redirect:/login"; return "user-form"; }

    @PostMapping("/users/save")
    public String saveUser(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String role,
                           HttpSession s){
        if(!isAdmin(s)) return "redirect:/login";
        User u = new User();
        u.setName(name); u.setEmail(email); u.setPassword(password); u.setRole(role);
        userService.register(u);
        System.out.println("Admin created user: " + email);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id, HttpSession s){
        if(!isAdmin(s)) return "redirect:/login";
        userService.delete(id);
        return "redirect:/admin/users";
    }

    // --- Create Task & Assign to multiple users ---
    @GetMapping("/tasks/new")
    public String newTaskPage(Model model, HttpSession s){
        if(!isAdmin(s)) return "redirect:/login";
        model.addAttribute("users", userService.all());
        return "task-form";
    }

    @PostMapping("/tasks/save")
    public String saveTask(@RequestParam String title,
                           @RequestParam String description,
                           @RequestParam String startDate,
                           @RequestParam String dueDate,
                           @RequestParam("userIds") List<Integer> userIds,
                           HttpSession s) throws Exception {
        if(!isAdmin(s)) return "redirect:/login";
        Task t = new Task();
        t.setTitle(title); t.setDescription(description);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date start = fmt.parse(startDate);
        Date due   = fmt.parse(dueDate);

        taskService.createTaskAndAssignToUsers(t, userIds, start, due);
        return "redirect:/admin/dashboard";
    }
}
