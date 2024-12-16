package com.learn.universityjpa.controller.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import java.util.List;

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

    @Schema(description = "Группы, содержащие данный предмет")
    @JsonProperty(value = "groups")
    private List<Long> groups;

    @Schema(description = "Преподаватели, обучающие данному предмету")
    @JsonProperty(value = "teachers")
    private List<Long> teachers;
}
