package org.su.framework.helper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.su.framework.bean.FileParam;
import org.su.framework.bean.FormParam;
import org.su.framework.bean.Param;
import org.su.framework.util.CollectionUtil;
import org.su.framework.util.FileUtil;
import org.su.framework.util.StreamUtil;
import org.su.framework.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class UploadHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);

    //apache提供的Servlet文件上传对象
    private static ServletFileUpload servletFileUpload;

    //初始化
    public static void init(ServletContext servletContext) {
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));
        int uploadLimit = ConfigHelper.getAppUploadLimit();
        if (uploadLimit != 0) {
            servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
        }
    }

    //判断是否为multipart类型请求
    public static boolean isMultipart(HttpServletRequest httpServletRequest) {
        return ServletFileUpload.isMultipartContent(httpServletRequest);
    }

    //创建请求对象，取出两种类型的参数
    public static Param createParam(HttpServletRequest request) throws IOException{
        List<FormParam> formParamList = new ArrayList<>();
        List<FileParam> fileParamList = new ArrayList<>();
        try {
            Map<String, List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);
            if (CollectionUtil.isNotEmpty(fileItemListMap)){
                for (Map.Entry<String, List<FileItem>> fileItemListEntry : fileItemListMap.entrySet()){
                    String fieldName = fileItemListEntry.getKey();
                    List<FileItem> fileItemList = fileItemListEntry.getValue();
                    if (CollectionUtil.isNotEmpty(fileItemList)){
                        for (FileItem fileItem : fileItemList){
                            //普通表单对象
                            if (fileItem.isFormField()){
                                String fieldValue = fileItem.getString("UTF-8");
                                formParamList.add(new FormParam(fieldName, fieldValue));
                            }else {
                                String fileName = FileUtil.getRealFileName(new String(fileItem.getName().getBytes(), "UTF-8"));
                                if (StringUtil.isNotEmpty(fileName)){
                                    long fileSize = fileItem.getSize();
                                    String contentType = fileItem.getContentType();
                                    InputStream inputStream = fileItem.getInputStream();
                                    fileParamList.add(new FileParam(fieldName, fileName, fileSize, contentType, inputStream));
                                }
                            }
                        }
                    }
                }
            }
        }catch (FileUploadException e){
            LOGGER.error("create param failure", e);
            throw new RuntimeException(e);
        }
        return new Param(formParamList, fileParamList);
    }

    //上传文件
    public static void uploadFile(String basePath, FileParam fileParam){
        try{
            if (fileParam != null){
                String filePath = basePath + fileParam.getFileName();
                FileUtil.createFile(filePath);
                InputStream inputStream = new BufferedInputStream(fileParam.getInputStream());
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
                StreamUtil.copyStream(inputStream, outputStream);
                LOGGER.error(filePath + " upload success");
            }
        }catch (Exception e){
            LOGGER.error("upload file failure", e);
            throw new RuntimeException(e);
        }
    }

    //批量上传文件
    public static void uploadFile(String basePath, List<FileParam> fileParamList){
        try{
            if (CollectionUtil.isNotEmpty(fileParamList)){
                for (FileParam fileParam : fileParamList){
                    uploadFile(basePath, fileParam);
                }
            }
        }catch (Exception e){
            LOGGER.error("upload file failure", e);
            throw new RuntimeException(e);
        }
    }
}
