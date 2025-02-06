package com.example.lmscourses.requests;

import jakarta.validation.constraints.NotEmpty;

public class CreateCourseRequest {
    @NotEmpty(message = "Course name is required")
    private String name;
    @NotEmpty(message = "Course code is required")
    private String code;
    @NotEmpty(message = "Course major is required")
    private String major;

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
}
