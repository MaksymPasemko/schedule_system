package com.example.schedule_system.controller;

import com.example.schedule_system.dto.dtorequest.GroupDTORequest;
import com.example.schedule_system.dto.dtoresponse.GroupDTO;
import com.example.schedule_system.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTORequest groupDTORequest){
        final GroupDTO groupDTO = groupService.createGroup(groupDTORequest);
        return ResponseEntity.ok(groupDTO);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GroupDTO> findGroupById(@PathVariable Long id){
        final GroupDTO groupDTO = groupService.findGroupById(id);
        return ResponseEntity.ok(groupDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GroupDTO> findGroupByName(@PathVariable String name){
        final GroupDTO groupDTO = groupService.findGroupByName(name);
        return ResponseEntity.ok(groupDTO);
    }

    @GetMapping
    public ResponseEntity<List<GroupDTO>> findGroups(){
        final List<GroupDTO> groupDTOS = groupService.findGroups();
        return ResponseEntity.ok(groupDTOS);
    }

    @PutMapping
    public ResponseEntity<GroupDTO> updateGroup(@RequestBody GroupDTORequest groupDTORequest){
        final GroupDTO groupDTO = groupService.updateGroup(groupDTORequest);
        return ResponseEntity.ok(groupDTO);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteGroupById(@PathVariable Long id){
        final boolean isDeleted = groupService.deleteGroupById(id);
        return isDeleted
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteGroupByName(@PathVariable String name){
        final boolean isDeleted = groupService.deleteGroupByName(name);
        return isDeleted
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
