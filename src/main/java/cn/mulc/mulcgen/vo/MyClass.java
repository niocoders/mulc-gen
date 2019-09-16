package cn.mulc.mulcgen.vo;

import lombok.Data;

import java.util.List;

/**
 * @ProjectName: mulc-gen
 * @Package: cn.mulc.mulcgen.vo
 * @ClassName: MyClass
 * @Author: llnlg
 * @Description:
 * @Date: 2019/9/10 15:26
 * @Version: 1.0
 */
@Data
public class MyClass {
    //类名
    private String className;
    //字段的集合
    private List<Field> fieldList;
   //模块名
    private String moudleName;
    //类注释
    private String tableComment;
    //作者
    private String author="zilong.xu";
    //时间
    private String localDate;
    //表名
    private String tableName;
    //fieldList的长度
    private int fieldSize;

    private String entityName;
    private String mapperName;
    private String serviceName;
}

