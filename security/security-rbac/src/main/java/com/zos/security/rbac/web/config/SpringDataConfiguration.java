package com.zos.security.rbac.web.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;

import java.util.List;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@SuppressWarnings("SpringJavaAutowiringInspection")
public class SpringDataConfiguration {

    @Bean
    public AlternateTypeRuleConvention pageableConvention(final TypeResolver resolver) {
        return new AlternateTypeRuleConvention() {
            @Override
            public int getOrder() {
                return Ordered.HIGHEST_PRECEDENCE;
            }
            @Override
            public List<AlternateTypeRule> rules() {
                return Lists.newArrayList(newRule(resolver.resolve(Pageable.class), resolver.resolve(Page.class)));
            }
        };
    }

    @ApiModel(value = "分页数据")
    static class Page {
        @ApiModelProperty(value = "页码[0..N]")
        private Integer page;

        @ApiModelProperty(value = "数量[1..1024)")
        private Integer size;

        @ApiModelProperty(value = "排序 sort=xx,yy(,asc|desc) 或者 sort=xx(,asc|desc)&sort=yy(,asc|desc)")
        private List<String> sort;

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public List<String> getSort() {
            return sort;
        }

        public void setSort(List<String> sort) {
            this.sort = sort;
        }
    }
}
