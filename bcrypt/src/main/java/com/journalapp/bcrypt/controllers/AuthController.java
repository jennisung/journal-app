package com.journalapp.bcrypt.controllers;

import com.journalapp.bcrypt.models.SiteUser;
import com.journalapp.bcrypt.repos.JournalRepository;
import com.journalapp.bcrypt.repos.SiteUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.mindrot.jbcrypt.BCrypt;

@Controller
public class AuthController {

    @Autowired
    SiteUserRepository siteUserRepository;

    @Autowired
    JournalRepository journalRepository;

    @GetMapping("/")
    public String getLogin() {
        return "login.html";
    }

    @PostMapping("/login")
    public RedirectView login(HttpServletRequest request, String userName, String password) {


        SiteUser siteUser = siteUserRepository.findSiteUserByUserName(userName);

        if(siteUser == null) {
            return new RedirectView("/");
        }

        if(!BCrypt.checkpw(password, siteUser.getPassword())) {
            return new RedirectView("/");
        }

        HttpSession session = request.getSession();
        session.setAttribute("userName", userName);

        return new RedirectView("/journals");
    }

    @PostMapping("/signup")
    public RedirectView signup(String userName, String password) {

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

        SiteUser newUser = new SiteUser(userName, hashedPassword);
        siteUserRepository.save(newUser);

        return new RedirectView("/");
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new RedirectView("/");
    }

//    @PostMapping("/logout")
//    public RedirectView logout(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        session.invalidate();
//        return new RedirectView("/");
//    }


}