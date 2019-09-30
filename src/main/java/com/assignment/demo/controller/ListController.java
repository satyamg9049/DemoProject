package com.assignment.demo.controller;

import com.assignment.demo.dto.ResponseDTO;
import com.assignment.demo.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ListController {

    private ListService listService;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping(path = "/getProjectList", produces = "application/json")
    public ResponseEntity<Object> getProjects() {
        List<ResponseDTO> result = new ArrayList<ResponseDTO>();
        try {
            List<ResponseDTO> gitLabProjects = listService.getGitLabProjects();
            List<ResponseDTO> gitHubProjects = listService.getGitHubProjects();
            result.addAll(gitLabProjects);
            result.addAll(gitHubProjects);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("status", "Error");
            errorMap.put("message", e.getMessage());
            return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
}
