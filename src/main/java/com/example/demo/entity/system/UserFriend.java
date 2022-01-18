package com.example.demo.entity.system;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author banyue
 * @since 2020-05-19
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_friend")
public class UserFriend extends Model<UserFriend> {

    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;
    private String friendId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
