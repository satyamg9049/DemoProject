package com.assignment.demo.dto;

import com.assignment.demo.RepoType;

import java.math.BigInteger;
import java.util.Date;

public class ResponseDTO {

    public BigInteger id;
    public String description;
    public String name;
    public Date createdAt;
    public RepoType repoType;

}
