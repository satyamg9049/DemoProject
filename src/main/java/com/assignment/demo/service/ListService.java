package com.assignment.demo.service;

import com.assignment.demo.RepoType;
import com.assignment.demo.dto.ResponseDTO;
import com.assignment.demo.util.RestUtil;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ListService {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public List<ResponseDTO> getGitHubProjects() throws Exception {
        List<ResponseDTO> responseDTOList = new ArrayList<ResponseDTO>();
        RestUtil restUtil = new RestUtil("https://api.github.com/users/satyamg9049/repos");
        ArrayList array = (ArrayList) restUtil.callGetApi();
        System.out.println(array);
        for (int i=0; i<array.size(); i++) {
            LinkedHashMap object = ((LinkedHashMap) array.get(i));
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.id = (BigInteger) object.get("id");
            responseDTO.name = (String) object.get("name");
            responseDTO.description = (String) object.get("description");
            responseDTO.createdAt = format.parse((String) object.get("created_at"));
            responseDTO.repoType = RepoType.GITHUB;
            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }

    public List<ResponseDTO> getGitLabProjects() throws Exception {
        List<ResponseDTO> responseDTOList = new ArrayList<ResponseDTO>();
        RestUtil restUtil = new RestUtil("https://gitlab.com/api/v4/users/gupta.satyam9049/projects");
        ArrayList array = (ArrayList) restUtil.callGetApi();
        System.out.println(array);
        for (int i=0; i<array.size(); i++) {
            LinkedHashMap object = ((LinkedHashMap) array.get(i));
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.id = (BigInteger) object.get("id");
            responseDTO.name = (String) object.get("name");
            responseDTO.description = (String) object.get("description");
            responseDTO.createdAt = format.parse((String) object.get("created_at"));
            responseDTO.repoType = RepoType.GITLAB;
            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }
}
