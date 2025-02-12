package com.learn.universityjpa.controller.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.universityjpa.controller.model.json.StudentJson;
import com.learn.universityjpa.controller.model.json.SubjectJson;
import com.learn.universityjpa.db.entity.Group;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Ответ по данным группы.
 */
@Schema(description = "Данные группы")
@Data
@Getter
@Setter
@NoArgsConstructor
@RedisHash("GroupResponse")
public class GroupResponse implements Serializable {

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

    public GroupResponse(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.specification = group.getSpecification();
        group.getSubjects().forEach(s->subjects.add(new SubjectJson(s)));
        group.getStudents().forEach(s->students.add(new StudentJson(s)));
    }
}
