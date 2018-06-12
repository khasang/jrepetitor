package io.khasang.jrepetitor.dto.impl;

import io.khasang.jrepetitor.dto.GroupDTOInterface;
import io.khasang.jrepetitor.dto.QuizDTOInterface;
import io.khasang.jrepetitor.entity.Group;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupDTOImpl implements GroupDTOInterface {

    private Long id;

    private List<QuizDTOInterface> quizes = new ArrayList<>();

    //topic name
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<QuizDTOInterface> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<QuizDTOInterface> quizes) {
        this.quizes = quizes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupDTOInterface getGroupDTO(Group group) {
        if (group == null) {
            return null;
        }
        GroupDTOInterface groupDTO = new GroupDTOImpl();
        groupDTO.setId(group.getId());
        groupDTO.setName(group.getName());
        return groupDTO;
    }

    public List<GroupDTOInterface> getGroupDTOList(List<Group> list) {
        List<GroupDTOInterface> groupDTOList = new ArrayList<>();
        for (Group group : list) {
            GroupDTOImpl groupDTOImpl = new GroupDTOImpl();
            groupDTOImpl.setId(group.getId());
            groupDTOImpl.setName(group.getName());

            groupDTOList.add(groupDTOImpl);
        }
        return groupDTOList;
    }

}
