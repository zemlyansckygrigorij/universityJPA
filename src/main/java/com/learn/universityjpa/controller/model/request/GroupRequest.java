package com.learn.universityjpa.controller.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.universityjpa.entity.Group;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Данные группы.
 */
@Schema(description = "Данные группы")
@Data
@AllArgsConstructor
public class GroupRequest {
    @Schema(description = "Идентификатор")
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    @Schema(description = "Название")
    private String name;

    @Schema(description = "Описание")
    @JsonProperty("specification")
    private String specification;

    public GroupRequest(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.specification = group.getSpecification();
    }
    public Group groupBuilder() {
        Group group = new Group();
        group.setName(this.name);
        group.setSpecification(this.specification);
        return group;
    }
}
