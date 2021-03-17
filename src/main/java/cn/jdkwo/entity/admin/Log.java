package cn.jdkwo.entity.admin;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * ϵͳ��־
 * @author llq
 *
 */
@Component
public class Log implements Serializable{
	private Long id;
	
	private String content;
	
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Log{" +
				"id=" + id +
				", content='" + content + '\'' +
				", createTime=" + createTime +
				'}';
	}
}

	

