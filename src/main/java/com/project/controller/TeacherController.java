package com.project.controller;


import com.project.model.ApiResponse;
import com.project.model.request.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    List<Teacher> teachers = Arrays.asList(
            new Teacher(1L, "John Doe", "Mathematics", "johndoe@example.com", "123-456-7890", "Java, Mobile Development, Web Development"),
            new Teacher(2L, "Jane Smith", "Computer Science", "janesmith@example.com", "234-567-8901", "Java, Android, Web Development"),
            new Teacher(3L, "Emily Johnson", "Physics", "emilyjohnson@example.com", "345-678-9012", "Java, Mobile Development"),
            new Teacher(4L, "Michael Brown", "Chemistry", "michaelbrown@example.com", "456-789-0123", "Java, Web Development"),
            new Teacher(5L, "Sarah Wilson", "English Literature", "sarahwilson@example.com", "567-890-1234", "JavaScript, HTML, CSS"),
            new Teacher(6L, "David Lee", "History", "davidlee@example.com", "678-901-2345", "None"),
            new Teacher(7L, "Linda Clark", "Biology", "lindaclark@example.com", "789-012-3456", "Java, Mobile Web Development"),
            new Teacher(8L, "James Adams", "Geography", "jamesadams@example.com", "890-123-4567", "Java, Web Development"),
            new Teacher(9L, "Robert Scott", "Philosophy", "robertscott@example.com", "901-234-5678", "None"),
            new Teacher(10L, "Lisa Turner", "Art and Design", "lisaturner@example.com", "012-345-6789", "JavaScript, Mobile Web Development")
    );

    @GetMapping // TODO http://localhost:8090/api/teacher?id=0&skill=Java, Mobile Web Development
    public ResponseEntity<?> getAllTeacher(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "skill", required = false) String skill
    ) {
        // Filter teachers based on id and skill
        List<Teacher> filteredTeachers = teachers.stream()
                .filter(teacher -> {
                    boolean matchesId = (id == null || id.isEmpty()) || teacher.getId().toString().equals(id);
                    boolean matchesSkill = (skill == null || skill.isEmpty()) || Teacher.containsSkill(teacher.getSkills(), skill);
                    return matchesId && matchesSkill;
                })
                .collect(Collectors.toList());

        // Check if there are any filtered teachers
        if (filteredTeachers.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No teachers found matching the criteria", 200, filteredTeachers));
        }

        return ResponseEntity.ok(new ApiResponse("OKAY", 200, filteredTeachers));
    }


}
