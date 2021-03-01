package top.flywen.wen.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import top.flywen.wen.entity.User;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    IPage<User> selectUsersByPage(IPage<User> pg, @Param("map") Map<String, Object> map);
}
