package com.worknest.controller;

import com.worknest.model.*;
import com.worknest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired private TaskService taskService;
    @Autowired private CommentService commentService;

    private User current(HttpSession s){ Object u=s.getAttribute("user"); return (u instanceof User)?(User)u:null; }

    @GetMapping("/home")
    public String home(Model model, HttpSession s){
        User u = current(s); if(u==null) return "redirect:/login";
        model.addAttribute("assignments", taskService.assignmentsForUser(u.getId()));
        return "user-dashboard";
    }

    @PostMapping("/assignment/{id}/status")
    public String updateStatus(@PathVariable Integer id, @RequestParam String status, HttpSession s){
        User u = current(s); if(u==null) return "redirect:/login";
        Status st = Status.valueOf(status);
        taskService.updateAssignmentStatus(id, st);
        return "redirect:/user/home";
    }

    @GetMapping("/assignment/{id}")
    public String assignmentDetails(@PathVariable Integer id, Model model, HttpSession s){
        User u = current(s); if(u==null) return "redirect:/login";
        model.addAttribute("assignmentId", id);
        model.addAttribute("comments", commentService.byAssignment(id));
        return "task-details";
    }

    @PostMapping("/assignment/{id}/comment")
    public String addComment(@PathVariable Integer id, @RequestParam String text, HttpSession s){
        User u = current(s); if(u==null) return "redirect:/login";
        Comment c = new Comment();
        Assignment a = new Assignment(); a.setId(id); // reference by id
        c.setAssignment(a); c.setUser(u); c.setCommentText(text);
        commentService.add(c);
        return "redirect:/user/assignment/"+id;
    }
}
