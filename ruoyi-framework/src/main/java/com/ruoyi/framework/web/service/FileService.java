package com.ruoyi.framework.web.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class FileService {

    /**
     * 判断文件是否存在，如果存在则删除，生成新的文件
     * @param filePath 文件路径
     * @param content 文件内容
     * @return 处理结果
     */
    public boolean handleFile(String filePath, String content) {
        Path path = Paths.get(filePath);

        try {
            // 检查父目录是否存在，如果不存在则创建
            Path parentDir = path.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            // 如果文件存在，先删除
            if (Files.exists(path)) {
                Files.delete(path);
            }

            // 创建新的文件并写入内容
            Files.write(path, content.getBytes(), StandardOpenOption.CREATE_NEW);
            return true;
        } catch (IOException e) {
            // 捕获 IO 异常并打印详细信息
            e.printStackTrace();
            return false;
        }
    }
}
