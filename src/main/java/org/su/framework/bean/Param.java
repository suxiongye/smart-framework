package org.su.framework.bean;

import org.su.framework.util.CastUtil;
import org.su.framework.util.CollectionUtil;
import org.su.framework.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Param {
    private List<FormParam> formParamList;
    private List<FileParam> fileParamList;

    public Param(List<FormParam> formParamList) {
        this.formParamList = formParamList;
    }

    public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
        this.formParamList = formParamList;
        this.fileParamList = fileParamList;
    }

    //根据参数名获取值
    public long getLong(String name) {
        return CastUtil.castLong(getFieldMap().get(name));
    }

    //获取请求参数映射
    public Map<String, Object> getFieldMap() {
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        if (CollectionUtil.isNotEmpty(formParamList)) {
            for (FormParam formParam : formParamList) {
                String fieldName = formParam.getFieldName();
                Object fieldValue = formParam.getFieldValue();
                if (fieldMap.containsKey(fieldName)) {
                    //如果已有参数则加入
                    fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
                }
                fieldMap.put(fieldName, fieldValue);
            }
        }
        return fieldMap;
    }

    //获取上传文件映射
    public Map<String, List<FileParam>> getFileMap() {
        Map<String, List<FileParam>> fileMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(fileParamList)) {
            for (FileParam fileParam : fileParamList) {
                String fieldName = fileParam.getFieldName();
                List<FileParam> tempFileParamList;
                //如果存在文件，则加入
                if (fileMap.containsKey(fieldName)) {
                    tempFileParamList = fileMap.get(fieldName);
                } else {
                    tempFileParamList = new ArrayList<>();
                }
                tempFileParamList.add(fileParam);
                fileMap.put(fieldName, fileParamList);
            }
        }
        return fileMap;
    }

    //根据参数获取所有文件
    public List<FileParam> getFileList(String fieldName) {
        return getFileMap().get(fieldName);
    }

    //获取唯一的上传文件，如果超过一个或没有则返回null
    public FileParam getFile(String fieldName) {
        List<FileParam> fileParamList = getFileList(fieldName);
        if (CollectionUtil.isNotEmpty(fileParamList) && fileParamList.size() == 1) {
            return fileParamList.get(0);
        }
        return null;
    }

    //验证参数是否为空
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(formParamList) && CollectionUtil.isEmpty(fileParamList);
    }

    //根据参数名获取String参数
    public String getString(String name) {
        return CastUtil.castString(getFieldMap().get(name));
    }

    //根据参数名获取double参数
    public double getDouble(String name){
        return CastUtil.castDouble(getFieldMap().get(name));
    }

    //根据参数名获取int参数
    public int getInt(String name){
        return CastUtil.castInt(getFieldMap().get(name));
    }
    //根据参数名获取boolean参数
    public boolean getBoolean(String name){
        return CastUtil.castBoolean(getFieldMap().get(name));
    }
}
