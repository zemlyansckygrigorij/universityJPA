package com.learn.universityjpa.controller.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Student;
import com.learn.universityjpa.entity.Subject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Ответ по данным группы.
 */
@Schema(description = "Данные группы")
@Data
@Getter
@Setter
@AllArgsConstructor
public class GroupResponse {

    @Schema(description = "Идентификатор группы")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Название группы")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Описание группы")
    @JsonProperty("specification")
    private String specification;

    @Schema(description = "Предметы данной группы")
    @JsonProperty(value = "subjects")
    private List<SubjectJson> subjects = new ArrayList<>();

    @Schema(description = "Студенты данной группы")
    @JsonProperty(value = "students")
    private List<StudentJson> students = new ArrayList<>();

    @Getter
    @Setter
    static class SubjectJson {
        private Long id;
        private String name;
        private String description;
        SubjectJson(Subject subject) {
           this.id = subject.getId();
           this.name = subject.getName();
           this.description = subject.getDescription();
        }
    }

    @Getter
    @Setter
    static class StudentJson {
        private Long id;
        private String firstName;
        private String secondName;
        private String lastName;
        private Date dateBirth;
        private String gender;
        StudentJson(Student student) {
            this.id = student.getId();
            this.firstName = student.getFirstName();
            this.secondName = student.getSecondName();
            this.lastName = student.getLastName();
            this.dateBirth = student.getDateBirth();
            this.gender = student.getGender().toString();
        }
    }

    public GroupResponse(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.specification = group.getSpecification();

        if (Optional.ofNullable(group.getStudents()).isPresent()) {
            this.students = group
                    .getStudents()
                    .stream()
                    .map(StudentJson::new)
                    .collect(Collectors.toList());
        }

        if (Optional.ofNullable(group.getSubjects()).isPresent()) {
            this.subjects = group
                    .getSubjects()
                    .stream()
                    .map(SubjectJson::new)
                    .collect(Collectors.toList());
        }
    }
}
