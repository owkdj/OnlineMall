package cn.jdkwo.dao.admin;

import cn.jdkwo.entity.admin.Menu;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface MenuDao {

    public int addMenu(Menu menu);
}
