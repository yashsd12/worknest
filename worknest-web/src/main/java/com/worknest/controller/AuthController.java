package com.worknest.controller;

import com.worknest.model.User;
import com.worknest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired private UserService userService;

    @GetMapping("/login")
    public String loginPage(){ return "login"; }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session,
                          Model model){
        User u = userService.login(email,password);
        if(u!=null){
            session.setAttribute("user", u);
            System.out.println("Logged in: " + u.getEmail() + " as " + u.getRole());
            if("ADMIN".equalsIgnoreCase(u.getRole())) return "redirect:/admin/dashboard";
            return "redirect:/user/home";
        }
        model.addAttribute("error","Invalid credentials!");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    // Simple self-register (defaults to USER). Admin can also add users from admin panel.
    @GetMapping("/register")
    public String registerPage(){ return "register"; }

    @PostMapping("/register")
    public String doRegister(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String password,
                             Model model){
        if(userService.login(email, password) != null){
            model.addAttribute("error","Email already exists!");
            return "register";
        }
        User u = new User();
        u.setName(name); u.setEmail(email); u.setPassword(password); u.setRole("USER");
        userService.register(u);
        System.out.println("Registered user: " + email);
        model.addAttribute("msg","Registered! Please login.");
        return "login";
    }
}
