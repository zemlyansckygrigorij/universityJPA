package com.learn.universityjpa.controller.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.universityjpa.entity.Subject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.learn.universityjpa.entity.Group;
import com.learn.universityjpa.entity.Teacher;

/**
 * Данные полученные с контроллера о предмете.
 */

@Schema(description = "Данные предметов")
@Data
@Getter
@AllArgsConstructor
public class SubjectResponse {
    @Schema(description = "Идентификатор предмета")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Название предмета")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Описание предмета")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Группы, содержащие данный предмет")
    @JsonProperty(value = "groups")
    private List<Long> groups;

    @Schema(description = "Преподаватели, обучающие данному предмету")
    @JsonProperty(value = "teachers")
    private List<Long> teachers;

    public SubjectResponse(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.description = subject.getDescription();
        if (Optional.ofNullable(subject.getGroups()).isPresent()) {
            this.groups = subject
                    .getGroups()
                    .stream()
                    .map(Group::getId)
                    .collect(Collectors.toList());
        }
        if (Optional.ofNullable(subject.getTeachers()).isPresent()) {
            this.teachers = subject
                    .getTeachers()
                    .stream()
                    .map(Teacher::getId)
                    .collect(Collectors.toList());
        }
    }
}
