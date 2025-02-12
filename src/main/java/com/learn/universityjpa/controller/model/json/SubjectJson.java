package com.learn.universityjpa.controller.model.json;

import com.learn.universityjpa.controller.model.response.SubjectResponse;
import com.learn.universityjpa.db.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class SubjectJson
 */
@Getter
@Setter
@NoArgsConstructor
public class SubjectJson {
    private Long id;
    private String name;
    private String description;

    public SubjectJson(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.description = subject.getDescription();
    }
    public SubjectJson(SubjectResponse response) {
        this.id = response.getId();
        this.name = response.getName();
        this.description = response.getDescription();
    }
}
