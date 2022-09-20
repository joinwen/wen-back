package top.flywen.asset.domain;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class MyPage<T> extends Page<T> {
    private static final long serialVersionUID = 1L;
    private Integer selectInt;
    private String selectStr;
    private String name;

    public MyPage(long current, long size) {
        super(current, size);
    }
}
