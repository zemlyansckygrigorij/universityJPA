package com.learn.universityjpa.controller.model.json;

import com.learn.universityjpa.db.entity.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class GroupJson
 */
@Getter
@Setter
@NoArgsConstructor
public class GroupJson {
    private Long id;
    private String name;
    private String specification;

    public GroupJson(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.specification = group.getSpecification();
    }
}
