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
//        String testdir = "/Users/robinluo/source/deeplink/app/";
        String debugdir = "/Users/robinluo/source/deeplink/app/build/outputs/apk/";
        String pubdir = "/root/";

        String fileName = "testdl.apk";
//        String fileName = "app-debug.apk";
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024 ];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(pubdir
                    , fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, i);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

}
