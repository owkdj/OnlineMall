package cn.jdkwo.entity.admin;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 菜单实体类
 */
@Component
public class Menu implements Serializable {

         private Long id;
         private Long parentId;//菜单父类id
         private Long _parentId;//父类id,匹配easyui父类id
         private String name;
         private String url;
         private String icon;//菜单图标

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long get_parentId() {
        _parentId = parentId;
        return _parentId;
    }

    public void set_parentId(Long _parentId) {
        this._parentId = _parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", _parentId=" + _parentId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
