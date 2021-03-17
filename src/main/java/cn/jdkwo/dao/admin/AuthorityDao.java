package cn.jdkwo.dao.admin;



import cn.jdkwo.entity.admin.Authority;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Ȩ��ʵ����dao
 * @author llq
 *
 */
@Repository
public interface AuthorityDao {
	 int add(Authority authority);
	 int deleteByRoleId(Long roleId);
	 List<Authority>
	 findListByRoleId(Long roleId);
}
