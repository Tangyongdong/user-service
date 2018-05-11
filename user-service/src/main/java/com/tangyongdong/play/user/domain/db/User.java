package com.tangyongdong.play.user.domain.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String token;

    private String name;

    private String phone;

    private Integer gender;

    private Integer status;

    private Integer sysAdmin;
}