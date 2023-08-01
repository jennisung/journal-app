package com.journalapp.bcrypt.controllers;

import com.journalapp.bcrypt.models.Journal;
import com.journalapp.bcrypt.models.SiteUser;
import com.journalapp.bcrypt.repos.JournalRepository;
import com.journalapp.bcrypt.repos.SiteUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class JournalController {

    @Autowired
    SiteUserRepository siteUserRepository;

    @Autowired
    JournalRepository journalRepository;

    @GetMapping("/journals")
    public String getJournals(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Object userNameAttribute = session.getAttribute("userName");

        if (userNameAttribute == null) {
            return "redirect:/";
        }

        String userName = userNameAttribute.toString();
        model.addAttribute("userName", userName);

        // Fetch the user from the database
        SiteUser user = siteUserRepository.findSiteUserByUserName(userName);

        // Fetch the user's journal entries from the database
        List<Journal> journalEntries = journalRepository.findBySiteUser(user);
        model.addAttribute("journals", journalEntries);

        return "journals.html";
    }

    @PostMapping("/addJournal")
    public RedirectView addJournal(HttpServletRequest request, String diary) {
        HttpSession session = request.getSession();
        Object userNameAttribute = session.getAttribute("userName");
        if (userNameAttribute == null) {
            return new RedirectView("/");
        }

        String userName = userNameAttribute.toString();
        SiteUser siteUser = siteUserRepository.findSiteUserByUserName(userName);

        Journal newJournal = new Journal(diary, LocalDate.now().toString());
        newJournal.setSiteUser(siteUser);
        journalRepository.save(newJournal);

        return new RedirectView("/journals");
    }
}
