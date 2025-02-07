package com.restapi.sompopo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    public Long id;
    public String username;
    public String password;
    public String email;
    public String msisdn;
}
