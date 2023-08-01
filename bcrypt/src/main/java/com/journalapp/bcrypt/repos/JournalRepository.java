package com.journalapp.bcrypt.repos;

import com.journalapp.bcrypt.models.Journal;
import com.journalapp.bcrypt.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalRepository extends JpaRepository<Journal, Long> {

    List<Journal> findBySiteUser(SiteUser user);
}
