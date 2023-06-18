package com.hl.javase.spi;

import java.util.List;

/**
 * @author huanglin
 * @date 2023/05/24 22:13
 */
public class FileSearch implements Search{

    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("文件搜索 "+keyword);
        return null;
    }
}
