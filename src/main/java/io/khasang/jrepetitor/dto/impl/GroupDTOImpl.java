package io.khasang.jrepetitor.dto.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.khasang.jrepetitor.dto.GroupDTOInterface;
import io.khasang.jrepetitor.dto.QuizDTOInterface;
import io.khasang.jrepetitor.dto.QuizPreviewDTOInterface;
import io.khasang.jrepetitor.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupDTOImpl implements GroupDTOInterface {

    @JsonIgnore
    @Autowired
    QuizPreviewDTOImpl quizPreviewDTO;

    private Long id;

    private List<QuizPreviewDTOInterface> quizzes = new ArrayList<>();

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

    public List<QuizPreviewDTOInterface> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<QuizPreviewDTOInterface> quizzes) {
        this.quizzes = quizzes;
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
        groupDTO.setQuizzes(quizPreviewDTO.getListQuizPreviewDTO(group.getQuizes()));
        return groupDTO;
    }

    public List<GroupDTOInterface> getGroupDTOList(List<Group> list) {
        List<GroupDTOInterface> groupDTOInterfaces = new ArrayList<>();
        if (list.isEmpty()) {
            return groupDTOInterfaces;
        }
        for (Group group : list) {
            groupDTOInterfaces.add(getGroupDTO(group));
        }
        return groupDTOInterfaces;
    }

    public Group getGroup(GroupDTOInterface group) {
        Group currentGroup = new Group();
        currentGroup.setName(group.getName());
        currentGroup.setId(group.getId());
        return currentGroup;
    }
}
