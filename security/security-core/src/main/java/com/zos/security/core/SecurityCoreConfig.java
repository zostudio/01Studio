package com.zos.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.zos.security.core.properties.SecurityProperties;

/**
 * 安全工程 -&gt; 核心模块配置项
 *
 * @author 01Studio
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
