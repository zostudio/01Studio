package com.zos.security.rbac.mapper;

import com.zos.security.rbac.bo.DepartmentBO;
import com.zos.security.rbac.bo.DepartmentConditionBO;
import com.zos.security.rbac.domain.Department;
import com.zos.security.rbac.dto.DepartmentDTO;
import com.zos.security.rbac.dto.condition.DepartmentConditionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
	
	DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
	
	Department boToDomain(DepartmentBO departmentBO);
	
	DepartmentBO domainToBO(Department department);
	
	List<DepartmentBO> domainToBO(List<Department> departments);
	
	DepartmentBO dtoToBO(DepartmentDTO departmentDTO);
	
	DepartmentDTO boToDTO(DepartmentBO departmentBO);
	
	List<DepartmentDTO> boToDTO(List<DepartmentBO> departmentBO);
	
	DepartmentConditionBO dtoToBO(DepartmentConditionDTO departmentConditionDTO);
	
}
