package com.zos.security.rbac.support.constance;

import org.springframework.http.HttpStatus;

public interface ResponseCode {

    /**
     * 服务成功处理
     */
    public final Integer SUCCESS = HttpStatus.OK.value();

    /**
     * 服务处理失败
     */
    public final Integer FAIL = HttpStatus.INTERNAL_SERVER_ERROR.value();

    /**
     * 请求的数据不存在
     */
    public final Integer INVALID = HttpStatus.BAD_REQUEST.value();
}
