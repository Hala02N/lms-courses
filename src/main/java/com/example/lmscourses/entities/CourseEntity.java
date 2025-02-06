package com.example.lmscourses.entities;

import com.yahoo.elide.annotation.Include;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "courses")
@Include(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "major")
    private String major;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date registrationDate;

    @Column(name = "updated_at", insertable = false)
    private Date updatedAt = new Date();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public Date getRegistrationDate() {
        return registrationDate;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
