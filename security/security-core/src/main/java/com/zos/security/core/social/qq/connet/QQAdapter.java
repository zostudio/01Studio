package com.zos.security.core.social.qq.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.zos.security.core.social.qq.api.QQ;
import com.zos.security.core.social.qq.api.QQUserInfo;

/**
 * 将第三方服务提供商查询到的用户信息封装到 Spring Oauth2 中的用户信息标准模型中
 *
 * @author 01Studio
 *
 */
public class QQAdapter implements ApiAdapter<QQ> {

	/**
	 * 测试接口连通性
	 *
	 * @param api 操作 QQ 用户信息的接口
	 * @return 布尔型数据
	 */
	@Override
	public boolean test(QQ api) {
		return true;
	}

	/**
	 * 将用户信息放入标准用户信息模型
	 *
	 * @param api 操作 QQ 用户信息的接口
	 * @param values 标准用户信息模型
	 */
	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		values.setProfileUrl(null);
		values.setProviderUserId(userInfo.getOpenId());
	}

	/**
	 * 获取用户个人主页数据
	 *
	 * @param api 操作 QQ 用户信息的接口
	 * @return 标准用户主页数据模型
	 */
	@Override
	public UserProfile fetchUserProfile(QQ api) {
		return null;
	}

	/**
	 * 更新用户状态
	 *
	 * @param api 操作 QQ 用户信息的接口
	 * @param message 消息
	 */
	@Override
	public void updateStatus(QQ api, String message) {
		//do noting
	}

}
