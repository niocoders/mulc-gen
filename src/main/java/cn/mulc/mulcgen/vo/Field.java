package cn.mulc.mulcgen.vo;

import lombok.Data;

/**
 * @ProjectName: mulc-gen
 * @Package: cn.mulc.mulcgen.vo
 * @ClassName: Field
 * @Author: llnlg
 * @Description:
 * @Date: 2019/9/10 15:23
 * @Version: 1.0
 */
@Data
public class Field  {
    //字段名
    private String fieldName;
    //字段类型
    private String fieldType;
    //字段注释
    private String fieldRemarks;
    //字段名首字母大写
    private String fieldNameUpperFirstLetter;



}