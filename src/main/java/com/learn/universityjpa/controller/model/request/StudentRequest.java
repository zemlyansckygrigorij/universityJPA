package com.learn.universityjpa.controller.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import java.util.Date;

/**
 * Запрос по данным студента.
 */
@Schema(description = "Данные студента")
@Data
@Getter
@AllArgsConstructor
public class StudentRequest {
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

    @Schema(description = "Дата рождения студента")
    @JsonProperty("dateBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateBirth;

    @Schema(description = "Пол студента")
    @JsonProperty("gender")
    private String gender;

    @Schema(description = "Идентификатор группы студента")
    @JsonProperty("groupId")
    private Long groupId;
}

