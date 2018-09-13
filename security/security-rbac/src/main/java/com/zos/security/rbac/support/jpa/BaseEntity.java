package com.zos.security.rbac.support.jpa;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 持久化类_基类
 */
@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity implements Serializable {

    /**
     * 数据库表主键
     */
    @Id
    @GeneratedValue(generator = "user-uuid")
    @Column(name = "id", nullable = false, length = 64)
    @GenericGenerator(name = "user-uuid", strategy = "uuid")
    private String id;

    /**
     * 审计日志, 记录条目创建时间, 自动赋值
     */
    @CreatedDate
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /**
     * 创建人
     */
    @CreatedBy
    @Column(nullable = false, length = 64)
    private String createdBy;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /**
     * 修改人
     */
    @LastModifiedBy
    @Column(nullable = false, length = 64)
    private String lastModifiedBy;
}
