package com.deepak.blog.dto;

import com.deepak.blog.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class JwtResponse {
    private String jwtToken;
    private String username;
    private List<String> roles;
}
