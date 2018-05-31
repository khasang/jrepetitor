package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.GroupDao;
import io.khasang.jrepetitor.dto.impl.GroupDTO;
import io.khasang.jrepetitor.entity.Group;
import io.khasang.jrepetitor.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("groupService")
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private GroupDTO groupDTO;

    @Override
    public Group addGroup(Group group) {
        return groupDao.create(group);
    }

    @Override
    public List<GroupDTO> getAllGroups() {
        return groupDTO.getGroupDTOList(groupDao.getList());
    }

    @Override
    public GroupDTO getGroupById(long id) {
        Group group = groupDao.getById(id);
        GroupDTO groupDTO = new GroupDTO().getGroupDTO(group);
        return groupDTO;
    }

    @Override
    public GroupDTO deleteGroup(long id) {
        Group group = groupDao.getById(id);
        if (group == null) {
            return null;
        }
        Group deletedGroup = groupDao.delete(group);
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(deletedGroup.getId());
        groupDTO.setName(deletedGroup.getName());
        return groupDTO;
    }

}
