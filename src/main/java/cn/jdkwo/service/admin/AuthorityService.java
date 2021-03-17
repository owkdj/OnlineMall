package cn.jdkwo.service.admin;

import java.util.List;

import cn.jdkwo.entity.admin.Authority;

/**
 * Ȩ��service�ӿ�
 * @author llq
 *
 */
public interface AuthorityService {
	 int add(Authority authority);
	 int deleteByRoleId(Long roleId);
	 List<Authority> findListByRoleId(Long roleId);
}
