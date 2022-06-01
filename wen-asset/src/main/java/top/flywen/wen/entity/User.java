package top.flywen.wen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
// mybatis-plus 使用
@TableName("wen_user")
// mybatis 通用 mapper
@Table(name = "wen_user")
public class User {
    // mybatis-plus 使用
    @TableId(type = IdType.AUTO)
    
    // mybatis 通用 mapper 使用
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private Integer gender;
    private String password;
    private String salt;
    private String avatar;
    private Integer isSys;
}
