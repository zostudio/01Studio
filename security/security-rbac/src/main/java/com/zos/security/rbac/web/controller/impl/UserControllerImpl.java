package com.zos.security.rbac.web.controller.impl;

import com.zos.security.rbac.bo.resopnse.base.UserBaseBO;
import com.zos.security.rbac.bo.resopnse.detail.UserDetailBO;
import com.zos.security.rbac.dto.common.ResponseDTO;
import com.zos.security.rbac.dto.param.base.UserParamBaseDTO;
import com.zos.security.rbac.dto.param.detail.UserParamDetailDTO;
import com.zos.security.rbac.dto.response.base.UserBaseDTO;
import com.zos.security.rbac.dto.response.detail.UserDetailDTO;
import com.zos.security.rbac.dto.response.info.UserInfoDTO;
import com.zos.security.rbac.mapper.base.UserBaseMapper;
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
    public ResponseDTO<Page<UserBaseDTO>> querySimple(UserParamBaseDTO userParamBaseDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 1024, sort = {"id"}) Pageable pageable) throws Exception {
        Page<UserBaseBO> userSimpleBOPage = userService.querySimple(UserParamMapper.INSTANCE.dtoToBO(userParamBaseDTO), pageable);
        Page<UserBaseDTO> userSimpleDTOPage = QueryResultConverter.convert(UserBaseMapper.INSTANCE.boToDTO(userSimpleBOPage.getContent()), pageable, userSimpleBOPage.getTotalElements());
        ResponseDTO<Page<UserBaseDTO>> responseDTO = new ResponseDTO<Page<UserBaseDTO>>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(userSimpleDTOPage);
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
    @ApiOperation(value = "查询本人账号安全详细信息")
	public ResponseDTO<Authentication> getAuthentication(Authentication authentication) {
        ResponseDTO<Authentication> responseDTO = new ResponseDTO<Authentication>();
        if (ConstantValidator.isNotNull(authentication)) {
            responseDTO.setCode(ResponseCode.SUCCESS);
            responseDTO.setData(authentication);
        } else {
            responseDTO.setCode(ResponseCode.FAIL);
        }
        return responseDTO;
	}

    @Override
    @ApiOperation(value = "查询账号是否存在")
    public ResponseDTO<Boolean> existByUsername(String username) {
        ResponseDTO<Boolean> responseDTO = new ResponseDTO<Boolean>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(userService.existByUsername(username));
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "查询邮箱是否存在")
    public ResponseDTO<Boolean> existByEmail(String email) {
        ResponseDTO<Boolean> responseDTO = new ResponseDTO<Boolean>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(userService.existByEmail(email));
        return responseDTO;
    }

    @Override
    @ApiOperation(value = "查询手机是否存在")
    public ResponseDTO<Boolean> existByPhone(String phone) {
        ResponseDTO<Boolean> responseDTO = new ResponseDTO<Boolean>();
        responseDTO.setCode(ResponseCode.SUCCESS);
        responseDTO.setData(userService.existByPhone(phone));
        return responseDTO;
    }
}
