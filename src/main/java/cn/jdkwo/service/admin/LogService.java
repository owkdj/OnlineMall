package cn.jdkwo.service.admin;

import java.util.List;
import java.util.Map;

import cn.jdkwo.entity.admin.Log;


/**
 * ��־�ӿ�
 * @author llq
 *
 */

public interface LogService {
	 int add(Log log);
	 int add(String content);
	 List<Log> findList(Map<String, Object> queryMap);
	 int getTotal(Map<String, Object> queryMap);
	 int delete(String ids);
}
