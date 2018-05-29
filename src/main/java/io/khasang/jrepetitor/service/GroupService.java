package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.GroupDTO;
import io.khasang.jrepetitor.entity.Group;

import java.util.List;

public interface GroupService {
    /**
     * method for add Group
     *
     * @param Group = new Group for creation in DB
     * @return created Group
     */
    Group addGroup(Group Group);

    /**
     * method for receiving all Groups
     *
     * @return all Groups
     */
    List<GroupDTO> getAllGroups();

    /**
     * method for receive specify Group by id
     *
     * @param id = uniq Group id
     * @return specify Group by id
     */
    GroupDTO getGroupById(long id);

    /**
     * method for Group delete
     *
     * @param id = Group's id for delete
     * @return removed Group
     */
    GroupDTO deleteGroup(long id);

}
