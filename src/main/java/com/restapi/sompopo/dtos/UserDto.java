package com.restapi.sompopo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    public Long id;
    public String username;
    public String password;
    public String email;
    public String msisdn;
}
