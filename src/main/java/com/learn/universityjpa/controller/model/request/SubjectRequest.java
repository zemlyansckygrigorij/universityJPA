package com.learn.universityjpa.controller.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.universityjpa.entity.Subject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Запрос по данным предмета.
 */
@Schema(description = "Данные о предмете")
@Data
@Getter
@AllArgsConstructor
public class SubjectRequest {
    @Schema(description = "Идентификатор предмета")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Название предмета")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Описание предмета")
    @JsonProperty("description")
    private String description;

    @Schema(description = "группы, содержащие данный предмет")
    @JsonProperty(value = "groups")
    private List<Long> groups;

    @Schema(description = "преподаватели, обучащие данному предмету")
    @JsonProperty(value = "teachers")
    private List<Long> teachers;


    public SubjectRequest(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.description = subject.getDescription();
        if (Optional.ofNullable(subject.getGroups()).isPresent()) {
            this.groups = subject
                    .getGroups()
                    .stream()
                    .map((x) -> x.getId())
                    .collect(Collectors.toList());
        }
        if (Optional.ofNullable(subject.getTeachers()).isPresent()) {
            this.teachers = subject
                    .getTeachers()
                    .stream()
                    .map((x) -> x.getId())
                    .collect(Collectors.toList());
        }
    }
}
