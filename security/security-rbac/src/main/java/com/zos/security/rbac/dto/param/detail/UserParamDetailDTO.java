package com.zos.security.rbac.dto.param.detail;


import com.zos.security.rbac.dto.param.base.UserParamBaseDTO;
import com.zos.security.rbac.support.enums.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(value = "账号详细信息查询条件")
public class UserParamDetailDTO extends UserParamBaseDTO {

    /**
     * 身份
     */
    @ApiModelProperty(value = "身份")
    private String identity;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private String dateOfBirth;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Gender gender;

    /**
     * 是否过期
     */
    @ApiModelProperty(value = "是否过期")
    private Boolean accountNonExpired;

    /**
     * 是否冻结
     */
    @ApiModelProperty(value = "是否冻结")
    private Boolean accountNonLocked;

    /**
     * 密码是否过期
     */
    @ApiModelProperty(value = "密码是否过期")
    private Boolean credentialsNonExpired;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Boolean enabled;

    /**
     * 创建时间开始
     */
    @ApiModelProperty(value = "创建时间开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDateStart;

    /**
     * 创建时间结束
     */
    @ApiModelProperty(value = "创建时间结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDateEnd;

    /**
     * 创建人员
     */
    @ApiModelProperty(value = "创建人员")
    private String createdBy;

    /**
     * 修改时间开始
     */
    @ApiModelProperty(value = "修改时间开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDateStart;

    /**
     * 修改时间结束
     */
    @ApiModelProperty(value = "修改时间结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDateEnd;

    /**
     * 修改人员
     */
    @ApiModelProperty(value = "修改人员")
    private String lastModifiedBy;
}
