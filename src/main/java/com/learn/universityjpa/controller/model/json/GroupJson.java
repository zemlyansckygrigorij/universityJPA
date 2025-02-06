package com.learn.universityjpa.controller.model.json;

import com.learn.universityjpa.controller.model.response.GroupResponse;
import com.learn.universityjpa.db.entity.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupJson {
    private Long id;
    private String name;
    private String specification;
    public GroupJson(Group group){
        this.id = group.getId();
        this.name = group.getName();
        this.specification = group.getSpecification();
    }

    public GroupResponse getGroupResponse(){
        GroupResponse response = new GroupResponse();
        response.setId(this.id);
        response.setName(this.name);
        response.setSpecification(this.specification);
        return response;
    }
}
