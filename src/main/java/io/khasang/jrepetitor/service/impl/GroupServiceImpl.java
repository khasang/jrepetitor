package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.GroupDao;
import io.khasang.jrepetitor.dto.GroupDTOInterface;
import io.khasang.jrepetitor.dto.impl.GroupDTOImpl;
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
    private GroupDTOImpl groupDTO;

    @Override
    public Group addGroup(Group group) {
        return groupDao.create(group);
    }

    @Override
    public List<GroupDTOInterface> getAllGroups() {
        return groupDTO.getGroupDTOList(groupDao.getList());
    }

    @Override
    public GroupDTOInterface getGroupById(long id) {
        return groupDTO.getGroupDTO(groupDao.getById(id));
    }

    @Override
    public GroupDTOInterface deleteGroup(long id) {
        Group group = groupDao.getById(id);
        if (group == null) {
            return null;
        }
        Group deletedGroup = groupDao.delete(group);
        GroupDTOImpl groupDTOImpl = new GroupDTOImpl();
        groupDTOImpl.setId(deletedGroup.getId());
        groupDTOImpl.setName(deletedGroup.getName());
        return groupDTOImpl;
    }

}
