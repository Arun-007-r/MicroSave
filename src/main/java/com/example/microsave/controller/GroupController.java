package com.example.microsave.controller;
import com.example.microsave.entity.Member;
import com.example.microsave.dto.GroupSummary;
import com.example.microsave.entity.Group;
import com.example.microsave.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // Create a new group
    @PostMapping
    public Group createGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }

    // Get all groups
    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    // Get group by ID
    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    // Get group financial summary
    @GetMapping("/{id}/summary")
    public GroupSummary getGroupSummary(@PathVariable Long id) {
        return groupService.getGroupSummary(id);
    }
    // Get all members of a group
    @GetMapping("/{id}/members")
    public List<Member> getGroupMembers(@PathVariable Long id) {
        return groupService.getGroupMembers(id);
    }
}