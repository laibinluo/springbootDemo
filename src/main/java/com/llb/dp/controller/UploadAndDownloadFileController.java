package com.llb.dp.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * created by robin on 2018/5/5.
 */

@Controller
@RequestMapping("/uploadAndDownload")
public class UploadAndDownloadFileController {

    @RequestMapping("/index")
    public String index(){
        return "download";
    }

    @RequestMapping(value = "/uploadFileAction", method = RequestMethod.POST)
    public ModelAndView uploadFileAction(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uploadAndDownload");

        InputStream fis = null;
        OutputStream outputStream = null;

        try {
            fis = uploadFile.getInputStream();
            outputStream = new FileOutputStream("/root/" + uploadFile.getOriginalFilename());

            IOUtils.copy(fis, outputStream);
            modelAndView.addObject("sucess", "上传成功");

            return modelAndView;

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fis != null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        modelAndView.addObject("sucess", "上传失败! ");
        return  modelAndView;
    }

    @RequestMapping("/downloadFileAction")
    public void downloadFileAction(HttpServletResponse request, HttpServletResponse response){
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;

        String test = "/Users/robinluo/source/deeplink/app/build/outputs/apk/testdl.apk";
        String pub = "/root/testdl.apk";

        try {
            File file = new File(test);
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
            response.flushBuffer();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fis != null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
