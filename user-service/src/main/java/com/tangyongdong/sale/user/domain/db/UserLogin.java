package com.tangyongdong.sale.user.domain.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {

    private String id;

    private String userId;

    private Date loginTime;

    private Integer status;
}