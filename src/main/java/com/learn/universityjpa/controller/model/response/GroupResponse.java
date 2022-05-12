package com.learn.universityjpa.controller.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.universityjpa.entity.Group;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Ответ по данным группы.
 */
@Schema(description = "Данные группы")
@Data
@AllArgsConstructor
public class GroupResponse {

    @Schema(description = "Идентификатор")
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    @Schema(description = "Название")
    private String name;

    @Schema(description = "Описание")
    @JsonProperty("specification")
    private String specification;

    public GroupResponse(Group group) {
     this.id = group.getId();
     this.name = group.getName();
     this.specification = group.getSpecification();
    }
}
