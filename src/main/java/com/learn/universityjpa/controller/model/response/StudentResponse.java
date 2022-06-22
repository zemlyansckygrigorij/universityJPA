package com.learn.universityjpa.controller.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.universityjpa.entity.Student;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Данные полученные с контроллера о студенте.
 */

public class StudentResponse {
    @Schema(description = "Идентификатор")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Имя")
    @JsonProperty("firstName")
    private String firstName;

    @Schema(description = "Отчество")
    @JsonProperty("secondName")
    private String secondName;

    @Schema(description = "Фамилия")
    @JsonProperty("lastName")
    private String lastName;

    @Schema(description = "Дата Рождения")
    @JsonProperty("dateBirth")
    private String dateBirth;

    @Schema(description = "Пол")
    @JsonProperty("gender")
    private String gender;

    @Schema(description = "Группа")
    @JsonProperty("group")
    private String groupName;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.secondName = student.getSecondName();
        this.lastName = student.getLastName();
        this.dateBirth = student.getDateBirth().toString();
        this.gender = student.getGender().toString();
        this.groupName = student.getGroup().getName();
    }
}
