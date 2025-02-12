package com.learn.universityjpa.controller.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.universityjpa.db.entity.Student;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;

/**
 * Данные полученные с контроллера о студенте.
 */
@Schema(description = "Данные студента")
@Data
@Getter
@Setter
@NoArgsConstructor
@RedisHash("StudentResponse")
public class StudentResponse implements Serializable {
    @Schema(description = "Идентификатор студента")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Имя студента")
    @JsonProperty("firstName")
    private String firstName;

    @Schema(description = "Отчество студента")
    @JsonProperty("secondName")
    private String secondName;

    @Schema(description = "Фамилия студента")
    @JsonProperty("lastName")
    private String lastName;

    @Schema(description = "Дата Рождения студента")
    @JsonProperty("dateBirth")
    private String dateBirth;

    @Schema(description = "Пол студента")
    @JsonProperty("gender")
    private String gender;

    @Schema(description = "Группа студента")
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
