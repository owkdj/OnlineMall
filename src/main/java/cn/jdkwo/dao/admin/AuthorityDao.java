package cn.jdkwo.dao.admin;



import cn.jdkwo.entity.admin.Authority;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 权限实体接口dao
 * @author llq
 *
 */
@Repository
public interface AuthorityDao {
	 int add(Authority authority);
	 int deleteByRoleId(Long roleId);
	 List<Authority>
	 findListByRoleId(@Param(value = "roleId") Long roleId);
}
