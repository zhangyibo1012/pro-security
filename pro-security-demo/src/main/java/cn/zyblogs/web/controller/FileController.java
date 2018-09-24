package cn.zyblogs.web.controller;

import cn.zyblogs.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Title: FileController.java
 * @Package cn.zyblogs.web.controller
 * @Description: TODO upload
 * @Author ZhangYB
 * @Version V1.0
 */
@RestController
@RequestMapping(value = "file")
public class FileController {


    private final String folder = "D:\\Program Files\\projects\\pro-security\\pro-security-demo\\src\\main\\java\\cn\\zyblogs\\web\\controller";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {

        // 传上来参数的名字
        System.out.println(file.getName());
        // 原始名字
        System.out.println(file.getOriginalFilename());
        // 文件大小
        System.out.println(file.getSize());


        // 存放文件夹 重新起一个文件名 时间戳
        File localFile = new File(folder, System.currentTimeMillis() + ".txt");

        // 把传上来的文件写到本地
        file.transferTo(localFile);

        // 绝对路径
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream outputStream = response.getOutputStream()) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");
            // commons-io
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }

    }
}
