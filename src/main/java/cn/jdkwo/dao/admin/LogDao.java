package cn.jdkwo.dao.admin;

import java.util.List;
import java.util.Map;

import cn.jdkwo.entity.admin.Log;
import org.springframework.stereotype.Repository;


/**
 * ϵͳ��־��dao
 * @author llq
 *
 */
@Repository
public interface LogDao {
	 int add(Log log);
	 List<Log> findList(Map<String, Object> queryMap);
	 int getTotal(Map<String, Object> queryMap);
	 int delete(String ids);
}
