package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Group;

import java.util.List;

public interface GroupDTOInterface {
    Long getId();

    void setId(Long id);

    List<QuizDTOInterface> getQuizes();

    void setQuizes(List<QuizDTOInterface> quizes);

    String getName();

    void setName(String name);

    GroupDTOInterface getGroupDTO(Group group);

    List<GroupDTOInterface> getGroupDTOList(List<Group> list);
}
