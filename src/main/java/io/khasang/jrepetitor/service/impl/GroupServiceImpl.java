package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.GroupDao;
import io.khasang.jrepetitor.dto.GroupDTO;
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
    public Group getGroupById(long id) {
        return groupDao.getById(id);
    }

    @Override
    public Group deleteGroup(long id) {
        return groupDao.delete(getGroupById(id));
    }
}
