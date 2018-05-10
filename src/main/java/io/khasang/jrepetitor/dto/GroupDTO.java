package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Group;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupDTO {

    private Long id;

    List<QuizDTO> quizes = new ArrayList<QuizDTO>();

    //topic name
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<QuizDTO> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<QuizDTO> quizes) {
        this.quizes = quizes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupDTO> getGroupDTOList(List<Group> list) {
        List<GroupDTO> groupDTOList = new ArrayList<>();

        for (Group group : list)
        {
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.setId(group.getId());
            groupDTO.setName(group.getName());

            groupDTOList.add(groupDTO);
        }
        return groupDTOList;
    }
    
}
