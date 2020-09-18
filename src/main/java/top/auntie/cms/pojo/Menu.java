package top.auntie.cms.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author Auntie
 * @Date 2020-09-08 17:52:40
 */

@Entity
@Table(name = "menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 6626637119079963886L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_path")
    private String menuPath;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "status")
    private Boolean status;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPath() {
        return this.menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                "menuName='" + menuName + '\'' +
                "menuPath='" + menuPath + '\'' +
                "createTime='" + createTime + '\'' +
                "updateTime='" + updateTime + '\'' +
                "status='" + status + '\'' +
                '}';
    }

}
