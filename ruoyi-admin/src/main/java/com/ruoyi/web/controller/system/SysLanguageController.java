package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.web.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/system/language")
public class SysLanguageController {

    @Autowired
    private FileService fileService;  // 注入 FileService

    @PostMapping("/update")
    public AjaxResult updateMappings(@RequestBody Map<String, Object> requestData) {
        // 从请求中获取 jsContent 字段，它是一个字符串
        String jsContent = (String) requestData.get("jsContent");

        // 输出接收到的 jsContent
        System.out.println("Received jsContent: " + jsContent);

        long timestamp = System.currentTimeMillis(); // 或使用 new Date().getTime()

        // 生成文件名（带有时间戳）
        String fileName = "language.js";

        // 修改文件路径，将文件存储到 "static" 目录之外（建议存储在项目的 "target" 目录下）
//        String filePath = "target/classes/static/" + fileName;
        String filePath = "target/classes/static/" + fileName;

        // 调用 FileService 处理文件
        boolean result = fileService.handleFile(filePath, jsContent);

        if (result) {
            return AjaxResult.success("File processed successfully, new file: " + fileName);
        } else {
            return AjaxResult.error("File processing failed");
        }
    }
}
