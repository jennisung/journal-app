package com.journalapp.bcrypt.models;


import jakarta.persistence.*;

@Entity
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String diary;
    private String date;

    @ManyToOne
    private SiteUser siteUser;

    public Journal(String diary, String date) {
        this.diary = diary;
        this.date = date;
    }

    public Journal() {

    }

    public long getId() {
        return id;
    }

    public String getDiary() {
        return diary;
    }

    public void setDiary(String diary) {
        this.diary = diary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", diary='" + diary + '\'' +
                ", date='" + date + '\'' +
                ", siteUser=" + siteUser +
                '}';
    }
}

