package com.zos.security.rbac.web.controller.impl;

import com.zos.security.rbac.bo.resopnse.base.RoleBaseBO;
import com.zos.security.rbac.bo.resopnse.base.RoleGroupBaseBO;
import com.zos.security.rbac.bo.resopnse.base.TeamBaseBO;
import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.UserDetailBO;
import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.UserParamBaseDTO;
import com.zos.security.rbac.dto.param.common.RelationsAddDTO;
import com.zos.security.rbac.dto.param.common.RelationsRemoveDTO;
import com.zos.security.rbac.dto.param.detail.UserParamDetailDTO;
import com.zos.security.rbac.dto.response.base.RoleBaseDTO;
import com.zos.security.rbac.dto.response.base.RoleGroupBaseDTO;
import com.zos.security.rbac.dto.response.base.TeamBaseDTO;
import com.zos.security.rbac.dto.response.base.UserBaseDTO;
import com.zos.security.rbac.dto.response.detail.UserDetailDTO;
import com.zos.security.rbac.dto.response.info.UserInfoDTO;
import com.zos.security.rbac.mapper.base.RoleBaseMapper;
import com.zos.security.rbac.mapper.base.RoleGroupBaseMapper;
import com.zos.security.rbac.mapper.base.TeamBaseMapper;
import com.zos.security.rbac.mapper.base.UserBaseMapper;
import com.zos.security.rbac.mapper.common.RelationsMapper;
import com.zos.security.rbac.mapper.detail.UserDetailMapper;
import com.zos.security.rbac.mapper.info.UserInfoMapper;
import com.zos.security.rbac.mapper.param.UserParamMapper;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.UserService;
import com.zos.security.rbac.support.constance.ResponseCode;
import com.zos.security.rbac.utils.ConstantValidator;
import com.zos.security.rbac.web.controller.UserController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Transactional
@RestController
@Api(description = "账号")
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;

    @Override
    @ApiOperation(value = "新建账号信息")
    public ResponseDTO<UserInfoDTO> create(@Valid @RequestBody UserInfoDTO userInfoDTO) {
        ResponseDTO<UserInfoDTO> responseDTO = new ResponseDTO<UserInfoDTO>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(UserInfoMapper.INSTANCE.boToDTO(userService.create(UserInfoMapper.INSTANCE.dtoToBO(userInfoDTO))));
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "更新账号信息")
    public ResponseDTO<UserInfoDTO> update(@ApiParam(value = "账号主键") @PathVariable String id, @Valid @RequestBody UserInfoDTO userInfoDTO) throws Exception {
        UserInfoDTO userInfoDTOResult = UserInfoMapper.INSTANCE.boToDTO(userService.update(id, UserInfoMapper.INSTANCE.dtoToBO(userInfoDTO)));
        ResponseDTO<UserInfoDTO> responseDTO = new ResponseDTO<UserInfoDTO>();
        if (ConstantValidator.isValuable(userInfoDTOResult.getId())) {
            responseDTO.setCode(ResponseCode.SUCCESS);
            responseDTO.setData(userInfoDTOResult);
        } else {
            responseDTO.setCode(ResponseCode.INVALID);
            responseDTO.setMessage("账号信息不存在");
        }
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "删除账号信息")
    public void delete(@ApiParam(value = "账号主键") @PathVariable String id) throws Exception {
        userService.delete(id);
    }

    @Override
    @ApiOperation(value = "查询账号详细信息")
    public ResponseDTO<UserDetailDTO> getInfo(@ApiParam(value = "账号主键") @PathVariable String id) throws Exception {
        ResponseDTO<UserDetailDTO> responseDTO = new ResponseDTO<UserDetailDTO>();
        UserDetailDTO userDetailDTO = UserDetailMapper.INSTANCE.boToDTO(userService.getInfo(id));
        if (userDetailDTO != null) {
            responseDTO.setCode(ResponseCode.SUCCESS);
            responseDTO.setData(userDetailDTO);
        } else {
            responseDTO.setCode(ResponseCode.INVALID);
            responseDTO.setMessage("账号信息不存在");
        }
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "查询账号基本信息")
    public ResponseDTO<Page<UserBaseDTO>> queryBase(UserParamBaseDTO userParamBaseDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
        Page<UserBaseBO> userBaseBOPage = userService.queryBase(UserParamMapper.INSTANCE.dtoToBO(userParamBaseDTO), pageable);
        Page<UserBaseDTO> userBaseDTOPage = QueryResultConverter.convert(UserBaseMapper.INSTANCE.boToDTO(userBaseBOPage.getContent()), pageable, userBaseBOPage.getTotalElements());
        ResponseDTO<Page<UserBaseDTO>> responseDTO = new ResponseDTO<Page<UserBaseDTO>>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(userBaseDTOPage);
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "查询账号详细信息")
    public ResponseDTO<Page<UserDetailDTO>> queryDetail(UserParamDetailDTO userParamDetailDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
        Page<UserDetailBO> userDetailBOPage = userService.queryDetail(UserParamMapper.INSTANCE.dtoToBO(userParamDetailDTO), pageable);
        Page<UserDetailDTO> userDetailDTOPage = QueryResultConverter.convert(UserDetailMapper.INSTANCE.boToDTO(userDetailBOPage.getContent()), pageable, userDetailBOPage.getTotalElements());
        ResponseDTO<Page<UserDetailDTO>> responseDTO = new ResponseDTO<Page<UserDetailDTO>>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(userDetailDTOPage);
        return responseDTO;
    }
    
	@Override
    @ApiOperation(value = "查询本人账号信息")
	public ResponseDTO<UserInfoDTO> getAuthentication(Authentication authentication) {
        ResponseDTO<UserInfoDTO> responseDTO = new ResponseDTO<UserInfoDTO>();
        if (ConstantValidator.isNotNull(authentication)) {
            responseDTO.setCode(ResponseCode.SUCCESS);
            if (authentication.getPrincipal() instanceof User) {
                ((User)authentication.getPrincipal()).setPassword(null);
                responseDTO.setData(UserInfoMapper.INSTANCE.boToDTO(UserInfoMapper.INSTANCE.domainToBO((User)authentication.getPrincipal())));
            } else {
                responseDTO.setData(null);
            }
        } else {
            responseDTO.setCode(ResponseCode.FAIL);
        }
        return responseDTO;
	}

    @Override
    @ApiOperation(value = "查询账号是否存在")
    public ResponseDTO<Boolean> existByUsername(@ApiParam(value = "账号") @PathVariable String username) {
        ResponseDTO<Boolean> responseDTO = new ResponseDTO<Boolean>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(userService.existByUsername(username));
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "查询邮箱是否存在")
    public ResponseDTO<Boolean> existByEmail(@ApiParam(value = "邮箱") @PathVariable String email) {
        ResponseDTO<Boolean> responseDTO = new ResponseDTO<Boolean>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(userService.existByEmail(email));
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "查询手机是否存在")
    public ResponseDTO<Boolean> existByPhone(@ApiParam(value = "手机") @PathVariable String phone) {
        ResponseDTO<Boolean> responseDTO = new ResponseDTO<Boolean>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(userService.existByPhone(phone));
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "查询成员角色")
    public ResponseDTO<Page<RoleBaseDTO>> queryRole(@ApiParam(value = "账号主键") @PathVariable String id, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
        Page<RoleBaseBO> roleBaseBOPage = userService.queryRole(id, pageable);
        Page<RoleBaseDTO> roleBaseDTOPage = QueryResultConverter.convert(RoleBaseMapper.INSTANCE.boToDTO(roleBaseBOPage.getContent()), pageable, roleBaseBOPage.getTotalElements());
        ResponseDTO<Page<RoleBaseDTO>> responseDTO = new ResponseDTO<Page<RoleBaseDTO>>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(roleBaseDTOPage);
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "批量添加成员角色")
    public ResponseDTO<Long> batchAddRole(@ApiParam(value = "账号主键") @PathVariable String id, @Valid @RequestBody RelationsAddDTO relationsAddDTO) throws Exception {
        Long result = userService.batchAddRole(id, RelationsMapper.INSTANCE.dtoToBO(relationsAddDTO));
        ResponseDTO<Long> responseDTO = new ResponseDTO<Long>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(result);
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "批量移除成员角色")
    public void batchRemoveRole(@ApiParam(value = "账号主键") @PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception {
        userService.batchRemoveRole(id, RelationsMapper.INSTANCE.dtoToBO(relationsRemoveDTO));
    }

    @Override
    @ApiOperation(value = "查询成员角色组")
    public ResponseDTO<Page<RoleGroupBaseDTO>> queryRoleGroup(@ApiParam(value = "账号主键") @PathVariable String id, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
        Page<RoleGroupBaseBO> roleGroupBaseBOPage = userService.queryRoleGroup(id, pageable);
        Page<RoleGroupBaseDTO> roleGroupBaseDTOPage = QueryResultConverter.convert(RoleGroupBaseMapper.INSTANCE.boToDTO(roleGroupBaseBOPage.getContent()), pageable, roleGroupBaseBOPage.getTotalElements());
        ResponseDTO<Page<RoleGroupBaseDTO>> responseDTO = new ResponseDTO<Page<RoleGroupBaseDTO>>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(roleGroupBaseDTOPage);
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "批量添加成员角色组")
    public ResponseDTO<Long> batchAddRoleGroup(@ApiParam(value = "账号主键") @PathVariable String id, @Valid @RequestBody RelationsAddDTO relationsAddDTO) throws Exception {
        Long result = userService.batchAddRoleGroup(id, RelationsMapper.INSTANCE.dtoToBO(relationsAddDTO));
        ResponseDTO<Long> responseDTO = new ResponseDTO<Long>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(result);
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "批量移除成员角色组")
    public void batchRemoveRoleGroup(@ApiParam(value = "账号主键") @PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception {
        userService.batchRemoveRoleGroup(id, RelationsMapper.INSTANCE.dtoToBO(relationsRemoveDTO));
    }

    @Override
    @ApiOperation(value = "查询成员归属团队")
    public ResponseDTO<Page<TeamBaseDTO>> queryTeam(@ApiParam(value = "账号主键") @PathVariable String id, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
        Page<TeamBaseBO> teamBaseBOPage = userService.queryTeam(id, pageable);
        Page<TeamBaseDTO> teamBaseDTOPage = QueryResultConverter.convert(TeamBaseMapper.INSTANCE.boToDTO(teamBaseBOPage.getContent()), pageable, teamBaseBOPage.getTotalElements());
        ResponseDTO<Page<TeamBaseDTO>> responseDTO = new ResponseDTO<Page<TeamBaseDTO>>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(teamBaseDTOPage);
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "批量关联成员团队")
    public ResponseDTO<Long> batchAddTeam(@ApiParam(value = "账号主键") @PathVariable String id, @Valid @RequestBody RelationsAddDTO relationsAddDTO) throws Exception {
        Long result = userService.batchAddTeam(id, RelationsMapper.INSTANCE.dtoToBO(relationsAddDTO));
        ResponseDTO<Long> responseDTO = new ResponseDTO<Long>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(result);
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "批量移除成员关联团队")
    public void batchRemoveTeam(@ApiParam(value = "账号主键") @PathVariable String id, @RequestBody RelationsRemoveDTO relationsRemoveDTO) throws Exception {
        userService.batchRemoveTeam(id, RelationsMapper.INSTANCE.dtoToBO(relationsRemoveDTO));
    }
}
