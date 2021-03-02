package top.flywen.wen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("wen_sys_log")
@Data
public class SysLog {
  @TableId(type = IdType.AUTO)
  private Integer id;
  private String username;
  private String operation;
  private String method;
  private String params;
  private String ip;
  private Date addTime;
  private Long responseTime;
}
