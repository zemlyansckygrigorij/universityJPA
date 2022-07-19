package com.learn.universityjpa.controller.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import java.util.Date;

/**
 * Запрос по данным преподавателя.
 */
@Schema(description = "Данные о преподавателе")
@Data
@Getter
@AllArgsConstructor
public class TeacherRequest {

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateBirth;

    @Schema(description = "Пол преподавателя")
    @JsonProperty("gender")
    private String gender;

    @Schema(description = "Категория преподавателя")
    @JsonProperty("category")
    private String category;
}
