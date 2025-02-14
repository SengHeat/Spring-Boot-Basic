package com.project.model.request;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Teacher {

    private Long id;
    private String name;
    private String subject;
    private String email;
    private String phoneNumber;
    private String skills;  // Added a skills field to match previous context

    // Constructor
    public Teacher(Long id, String name, String subject, String email, String phoneNumber, String skills) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.skills = skills;
    }

    // Getters and Setters for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    // toString method to display Teacher details
    @Override
    public String toString() {
        return "Teacher{id=" + id + ", name='" + name + "', subject='" + subject + "', email='" + email + "', phoneNumber='" + phoneNumber + "', skills='" + skills + "'}";
    }

    // Fuzzy match function
    // Helper method to check if a teacher's skills contain the provided search term with fuzzy matching
    private static boolean fuzzyMatch(String value, String searchValue) {
        if (searchValue.isEmpty()) return true;
        if (value.isEmpty()) return false;

        int searchIndex = 0;
        for (int i = 0; i < value.length(); i++) {
            if (Character.toLowerCase(value.charAt(i)) == Character.toLowerCase(searchValue.charAt(searchIndex))) {
                searchIndex++;
            }
            if (searchIndex == searchValue.length()) {
                return true;
            }
        }
        return false;
    }

    // This method splits skills and applies fuzzy matching
    public static boolean containsSkill(String teacherSkills, String searchSkill) {
        // Convert both the teacher's skills and search term to lowercase for case-insensitive matching
        if (teacherSkills == null || searchSkill == null) {
            return false;
        }

        // Split skills by comma and apply fuzzy matching to each skill
        return Arrays.stream(teacherSkills.split(","))
                .map(String::trim)  // Trim any leading/trailing spaces
                .anyMatch(skill -> fuzzyMatch(skill, searchSkill)); // Apply fuzzy matching to each skill
    }

}
