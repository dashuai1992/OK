package com.very.ok.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author YDS
 * @since 2021-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 姓
     */
    private String userFirstName;

    /**
     * 名
     */
    private String userSecondName;

    /**
     * 密码
     */
    private String userPwd;

    /**
     * 简介
     */
    private String userIntro;

    /**
     * 头像
     */
    private String userAvatar;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Long created;

    /**
     * 更最近新时间
     */
    private Long updated;

    /**
     * 创建人
     */
    private Long createBy;


}
