package com.zos.security.rbac.dto.param.detail;


import com.zos.security.rbac.dto.param.base.TeamParamBaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(value = "团队详细信息查询条件")
public class TeamParamDetailDTO extends TeamParamBaseDTO {

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
