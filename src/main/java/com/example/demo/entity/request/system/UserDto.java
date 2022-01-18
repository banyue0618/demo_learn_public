package com.example.demo.entity.request.system;

import com.example.demo.common.support.excel.ExcelVOAttribute;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author banyue
 * date 2020-03-31
 */
@Data
public class UserDto {
    @ExcelVOAttribute(name="工号", column="A")
    @NotNull(message = "工号不能为空")
    private String no;
    @ExcelVOAttribute(name="用户名", column="B")
    private String loginName;
    private String password;
    @ExcelVOAttribute(name="邮箱", column="C")
    private String email;
    @ExcelVOAttribute(name="座机号码", column="D")
    private String mobile;
    @ExcelVOAttribute(name="手机号码", column="E")
    private String phone;
    @ExcelVOAttribute(name="部门", column="F")
    private String office;
}
