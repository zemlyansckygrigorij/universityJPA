package com.learn.universityjpa.controller.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.universityjpa.controller.model.json.SubjectJson;
import com.learn.universityjpa.db.entity.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Данные полученные с контроллера об преподавателе.
 */
@Schema(description = "Данные об преподавателе")
@Data
@Getter
@Setter
@NoArgsConstructor
@RedisHash("TeacherResponse")
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
    private List<SubjectJson> subjects = new ArrayList<>();

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
                    .map(SubjectJson::new)
                    .collect(Collectors.toList());
        }
    }
}
