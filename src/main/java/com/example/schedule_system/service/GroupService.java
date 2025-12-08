package com.example.schedule_system.service;

import com.example.schedule_system.dto.dtorequest.GroupDTORequest;
import com.example.schedule_system.dto.dtoresponse.GroupDTO;
import com.example.schedule_system.model.entity.Group;
import com.example.schedule_system.repository.GroupRepository;
import com.example.schedule_system.util.mapper.GroupMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.schedule_system.util.mapper.GroupMapper.convertGroupToGroupDTO;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupDTO createGroup(GroupDTORequest groupDTORequest){
        final Group group = new Group();
        group.setName(groupDTORequest.getName());
        group.setCreatedAt(groupDTORequest.getCreatedAt());
        group.setGroupSize(groupDTORequest.getGroupSize());
        group.setGroupLeader(groupDTORequest.getGroupLeader());

        groupRepository.save(group);
        return convertGroupToGroupDTO(group);
    }

    public GroupDTO findGroupById(Long id){
        final Group group = groupRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return convertGroupToGroupDTO(group);
    }
    public GroupDTO findGroupByName(String name){
        final Group group = groupRepository.findByName(name)
                .orElseThrow(EntityNotFoundException::new);
        return convertGroupToGroupDTO(group);
    }

    public List<GroupDTO> findGroups(){
        final List<Group> groups = groupRepository.findAll();
        return groups.stream()
                .map(GroupMapper::convertGroupToGroupDTO).toList();
    }

    public GroupDTO updateGroup(GroupDTORequest groupDTORequest){
        final Group group = groupRepository.findByName(groupDTORequest.getName())
                .orElseThrow(EntityNotFoundException::new);
        group.setName(groupDTORequest.getName());
        group.setCreatedAt(groupDTORequest.getCreatedAt());
        group.setGroupSize(groupDTORequest.getGroupSize());
        group.setGroupLeader(groupDTORequest.getGroupLeader());

        groupRepository.save(group);
        return convertGroupToGroupDTO(group);
    }
    public boolean deleteGroupById(Long id){
        if(groupRepository.existsById(id)){
            groupRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteGroupByName(String name){
        if(groupRepository.existsByName(name)){
            groupRepository.deleteByName(name);
            return true;
        }
        return false;
    }

}
