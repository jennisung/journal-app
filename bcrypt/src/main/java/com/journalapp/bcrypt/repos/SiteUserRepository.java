package com.journalapp.bcrypt.repos;

import com.journalapp.bcrypt.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
    public SiteUser findSiteUserByUserName(String userName);
}
