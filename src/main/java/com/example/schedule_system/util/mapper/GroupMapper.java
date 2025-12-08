package com.example.schedule_system.util.mapper;

import com.example.schedule_system.dto.dtorequest.GroupDTORequest;
import com.example.schedule_system.dto.dtoresponse.GroupDTO;
import com.example.schedule_system.model.entity.Group;

public class GroupMapper {
    public static GroupDTO convertGroupToGroupDTO(Group group){
        return new GroupDTO(group.getId(),group.getName(),group.getCreatedAt(),
                group.getGroupSize(),group.getGroupLeader());
    }
}
