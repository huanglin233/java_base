package com.hl.javase.spi;

import java.util.List;

/**
 * @author huanglin
 * @date 2023/05/24 22:47
 */
public class DatabaseSearch implements Search{

    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("数据搜索 "+keyword);
        return null;
    }
}
