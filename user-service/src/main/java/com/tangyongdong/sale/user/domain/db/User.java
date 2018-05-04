package com.tangyongdong.sale.user.domain.db;

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

    private String name;

    private String phone;

    private String gender;

    private Integer status;

    private Integer admin;
}