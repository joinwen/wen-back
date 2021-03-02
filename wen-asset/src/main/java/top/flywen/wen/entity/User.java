package top.flywen.wen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("wen_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private Integer gender;
    private String password;
    private String salt;
    private String avatar;
    private Integer isSys;
}
