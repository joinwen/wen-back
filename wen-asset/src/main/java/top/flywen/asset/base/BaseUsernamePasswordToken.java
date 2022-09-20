package top.flywen.asset.base;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

@Data
public class BaseUsernamePasswordToken extends UsernamePasswordToken {
    private Integer isSys;

    public Integer getSys() {
        return isSys;
    }

    public void setSys(Integer sys) {
        isSys = sys;
    }
}
