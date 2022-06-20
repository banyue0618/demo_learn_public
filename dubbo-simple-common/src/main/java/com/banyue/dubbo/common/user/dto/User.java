package com.banyue.dubbo.common.user.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName UserDO
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/27 9:04
 * @Version 1.0
 */
@Entity
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String userName;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private BigDecimal balance;

}
