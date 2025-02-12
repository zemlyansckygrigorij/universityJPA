package com.learn.universityjpa.controller.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.universityjpa.controller.model.json.GroupJson;
import com.learn.universityjpa.controller.model.json.TeacherJson;
import com.learn.universityjpa.db.entity.Subject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import java.util.ArrayList;
import java.util.List;


/**
 * Данные полученные с контроллера о предмете.
 */

@Schema(description = "Данные предметов")
@Data
@Getter
@Setter
@NoArgsConstructor
@RedisHash("SubjectResponse")
public class SubjectResponse {
    @Schema(description = "Идентификатор предмета")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Название предмета")
    @JsonProperty("name1")
    private String name;

    @Schema(description = "Описание предмета")
    @JsonProperty("description1")
    private String description;

    @Schema(description = "Группы, содержащие данный предмет")
    @JsonProperty(value = "groups")
    private List<GroupJson> groups = new ArrayList<>();

    @Schema(description = "Преподаватели, обучающие данному предмету")
    @JsonProperty(value = "teachers")
    private List<TeacherJson> teachers = new ArrayList<>();

    public SubjectResponse(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.description = subject.getDescription();
        if (subject.getGroups() != null) {
            this.groups = subject.getGroups().stream().map(GroupJson::new).toList();
        }

        if (subject.getTeachers() != null) {
            this.teachers = subject.getTeachers().stream().map(TeacherJson::new).toList();
        }
    }
}
