package com.learn.universityjpa.controller.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.universityjpa.entity.Subject;
import com.learn.universityjpa.entity.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Данные полученные с контроллера об преподавателе.
 */
@Schema(description = "Данные об преподавателе")
@Data
@Getter
@AllArgsConstructor
public class TeacherResponse {
    @Schema(description = "Идентификатор преподавателя")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Имя преподавателя")
    @JsonProperty("firstName")
    private String firstName;

    @Schema(description = "Отчество преподавателя")
    @JsonProperty("secondName")
    private String secondName;

    @Schema(description = "Фамилия преподавателя")
    @JsonProperty("lastName")
    private String lastName;

    @Schema(description = "Дата рождения преподавателя")
    @JsonProperty("dateBirth")
    private String dateBirth;

    @Schema(description = "Пол преподавателя")
    @JsonProperty("gender")
    private String gender;

    @Schema(description = "Категория преподавателя")
    @JsonProperty("category")
    private  String category;

    @Schema(description = "Предметы преподавателя")
    @JsonProperty(value = "subjects")
    private List<SubjectJson> subjects;

    class SubjectJson {
        private Long id;
        private String name;
        private String description;
        SubjectJson(Subject subject) {
            this.id = subject.getId();
            this.name = subject.getName();
            this.description = subject.getDescription();
        }
    }

    public TeacherResponse(Teacher teacher) {
        this.id = teacher.getId();
        this.firstName = teacher.getFirstName();
        this.secondName = teacher.getSecondName();
        this.lastName = teacher.getLastName();
        this.category = teacher.getCategory();
        this.dateBirth = teacher.getDateBirth().toString();
        this.gender = teacher.getGender().toString();
        if (Optional.ofNullable(teacher.getSubjects()).isPresent()) {
            this.subjects = teacher
                    .getSubjects()
                    .stream()
                    .map((x) -> new SubjectJson(x))
                    .collect(Collectors.toList());
        }
    }
}
