package cn.mulc.mulcgen.gen;

import cn.mulc.mulcgen.vo.MyClass;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: mulc-gen
 * @Package: cn.mulc.mulcgen.gen
 * @ClassName: GenCode
 * @Author: llnlg
 * @Description: 生成代码工具类
 * @Date: 2019/9/11 11:23
 * @Version: 1.0
 */
public class GenCode {
    /**
     * 根据数字确定生成路径，
     *
     * @param choseType  0为项目目录 1为 系统目录
     * @param moudleName 前台传过来的模块名称
     * @param className
     * @param SystemPath 如果选择代码生成在系统目录，定义的系统目录
     * @return
     */
    public static String getPath(int choseType, String moudleName, String className, String TypeName, String SystemPath) {
        //如果选择项目目录
        if (choseType == 0) {
            String projectPath = System.getProperty("user.dir");

            String[] moudleNames = fileUtil.getMoudleName(moudleName);

            String path = projectPath + "/src/main/java/" + moudleNames[0] + "/" + moudleNames[1] + "/" + moudleNames[2] + "/" + TypeName + "/" + className + ".java";
            //判断entity路径是否存在，如果不存在，则新建entity文件夹
            String path1 = projectPath + "/src/main/java/" + moudleNames[0] + "/" + moudleNames[1] + "/" + moudleNames[2] + "/" + TypeName + "/";
            fileUtil.mkdirsFile(path1);
            return path;

        } else if (choseType == 1) {
            String path = SystemPath + "/" + TypeName + "/" + className + ".java";
            //如果entity目录不存在，则建立
            String path1 = SystemPath + "/" + TypeName + "/";
            fileUtil.mkdirsFile(path1);
            return path;

        }
        return "请选择系统目录或者是项目目录";


    }


    /**
     * 配置freemarker模板
     *
     * @return
     * @throws IOException
     */
    public static Configuration getCfg() throws IOException {
        //创建Configuration对象
        Configuration configuration = new Configuration();
        //设置模板所在目录
        String path = GenCode.class.getClassLoader().getResource("templates/gen/").getPath();

        configuration.setDirectoryForTemplateLoading(new File(path));
        return configuration;
    }


    /**
     * 生成实体类
     *
     * @throws IOException
     */
    public static void genEntity(String TableName, String moudleName) throws IOException, TemplateException, SQLException {
        //找到实体类模板
        Template template = getCfg().getTemplate("entity.ftl");
        //设置数据并执行
        Map map = new HashMap();
        //根据表名，获取所有的表信息
        MyClass myClass = DataUtils.getColumnInfo(TableName, moudleName);
        myClass.setClassName(myClass.getClassName() + "Entity");
        //将获取到的表信息放入map中
        map.put("myClass", myClass);
        //自定义生成文件存放的系统目录
        String SystemPath = "d:";
        //获取文件生成的路径
        String path = getPath(0, moudleName, myClass.getClassName(), "entity", SystemPath);

        //如果文件不存在，则进行生成，如果存在，则忽略
        if (!fileUtil.isHave(path)) {
            //输出位置为工作空间
            Writer writer = new OutputStreamWriter(new FileOutputStream(path));
            //模板执行
            template.process(map, writer);
            System.out.println(myClass.getClassName() + "实体类生成成功");
        }


    }

    /**
     * 根据表名和模块名生成mapper接口
     *
     * @param TableName
     * @param moudleName
     */
    public static void genMapper(String TableName, String moudleName) throws IOException, SQLException, TemplateException {
        //调用配置mapper.ftl模板
        Template template = getCfg().getTemplate("mapper.ftl");
        //设置数据并执行
        Map map = new HashMap();
        //根据表名，获取所有的表信息
        MyClass myClass = DataUtils.getColumnInfo(TableName, moudleName);
        myClass.setEntityName(myClass.getClassName() + "Entity");
        myClass.setClassName(myClass.getClassName() + "Mapper");

        //将获取到的表信息放入map中
        map.put("myClass", myClass);
        //自定义生成文件存放的系统目录
        String SystemPath = "d:";

        String path = getPath(0, moudleName, myClass.getClassName(), "mapper", SystemPath);
        //如果文件不存在，则进行生成，如果存在，则忽略
        if (!fileUtil.isHave(path)) {
            //输出位置为工作空间
            Writer writer = new OutputStreamWriter(new FileOutputStream(path));
            //模板执行
            template.process(map, writer);
            System.out.println(myClass.getClassName() + "mapper生成成功");
        }
    }

    /**
     * 生成xml
     *
     * @param TableName
     * @param moudleName
     * @throws IOException
     * @throws SQLException
     */
    public static void genXml(String TableName, String moudleName) throws IOException, SQLException, TemplateException {
        //调用配置mapper.ftl模板
        Template template = getCfg().getTemplate("xml.ftl");
        //设置数据并执行
        Map map = new HashMap();
        //根据表名，获取所有的表信息
        MyClass myClass = DataUtils.getColumnInfo(TableName, moudleName);
        myClass.setEntityName(myClass.getClassName() + "Entity");
        myClass.setMapperName(myClass.getClassName() + "Mapper");
        myClass.setClassName(myClass.getClassName() + "");
        myClass.setTableName(TableName);
        //将获取到的表信息放入map中
        map.put("myClass", myClass);
        //自定义生成文件存放的系统目录
        String SystemPath = "d:";
        String path1 = System.getProperty("user.dir") + "\\src\\main\\resources\\mapper\\";
        fileUtil.mkdirsFile(path1);
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\mapper\\" + myClass.getClassName() + "Mapper.xml";
        //如果文件不存在，则进行生成，如果存在，则忽略
        if (!fileUtil.isHave(path)) {
            //输出位置为工作空间
            Writer writer = new OutputStreamWriter(new FileOutputStream(path));
            //模板执行
            template.process(map, writer);
            System.out.println(myClass.getClassName() + "xml生成成功");
        }

    }

    /**
     * 生成service
     *
     * @param TableName
     * @param moudleName
     */
    public static void genService(String TableName, String moudleName) throws IOException, SQLException, TemplateException {
        //调用配置mapper.ftl模板
        Template template = getCfg().getTemplate("service.ftl");
        //设置数据并执行
        Map map = new HashMap();
        //根据表名，获取所有的表信息
        MyClass myClass = DataUtils.getColumnInfo(TableName, moudleName);
        myClass.setEntityName(myClass.getClassName() + "Entity");
        myClass.setClassName(myClass.getClassName() + "Service");

        //将获取到的表信息放入map中
        map.put("myClass", myClass);
        //自定义生成文件存放的系统目录
        String SystemPath = "d:";

        String path = getPath(0, moudleName, myClass.getClassName(), "service", SystemPath);
        //如果文件不存在，则进行生成，如果存在，则忽略
        if (!fileUtil.isHave(path)) {
            //输出位置为工作空间
            Writer writer = new OutputStreamWriter(new FileOutputStream(path));
            //模板执行
            template.process(map, writer);
            System.out.println(myClass.getClassName() + "service生成成功");
        }

    }

    /**
     * 生成ServiceImpl
     *
     * @param TableName
     * @param moudleName
     */
    public static void genServiceImpl(String TableName, String moudleName) throws IOException, SQLException, TemplateException {
        //调用配置mapper.ftl模板
        Template template = getCfg().getTemplate("serviceimpl.ftl");
        //设置数据并执行
        Map map = new HashMap();
        //根据表名，获取所有的表信息
        MyClass myClass = DataUtils.getColumnInfo(TableName, moudleName);
        myClass.setEntityName(myClass.getClassName() + "Entity");
        myClass.setServiceName(myClass.getClassName() + "Service");
        myClass.setMapperName(myClass.getClassName() + "Mapper");
        myClass.setClassName(myClass.getClassName() + "ServiceImpl");


        //将获取到的表信息放入map中
        map.put("myClass", myClass);
        //自定义生成文件存放的系统目录
        String SystemPath = "d:";

        String path = getPath(0, moudleName, myClass.getClassName(), "service/impl", SystemPath);
        //如果文件不存在，则进行生成，如果存在，则忽略
        if (!fileUtil.isHave(path)) {
            //输出位置为工作空间
            Writer writer = new OutputStreamWriter(new FileOutputStream(path));
            //模板执行
            template.process(map, writer);
            System.out.println(myClass.getClassName() + "serviceimpl生成成功");
        }

    }

    /**
     * 生成controller代码
     * @param TableName
     * @param moudleName
     */
    public static void genController(String TableName, String moudleName) throws IOException, SQLException, TemplateException {
        //调用配置mapper.ftl模板
        Template template = getCfg().getTemplate("controller.ftl");
        //设置数据并执行
        Map map = new HashMap();
        //根据表名，获取所有的表信息
        MyClass myClass = DataUtils.getColumnInfo(TableName, moudleName);
        myClass.setEntityName(myClass.getClassName() + "Entity");
        myClass.setServiceName(myClass.getClassName()+"Service");
        myClass.setClassName(myClass.getClassName() + "Controller");
        myClass.setTableName(TableName);

        //将获取到的表信息放入map中
        map.put("myClass", myClass);
        //自定义生成文件存放的系统目录
        String SystemPath = "d:";

        String path = getPath(0, moudleName, myClass.getClassName(), "controller", SystemPath);

        //如果文件不存在，则进行生成，如果存在，则忽略
        if (!fileUtil.isHave(path)) {
            //输出位置为工作空间
            Writer writer = new OutputStreamWriter(new FileOutputStream(path));
            //模板执行
            template.process(map, writer);
            System.out.println(myClass.getClassName() + "controllrt生成成功");
        }

    }

    public static void gen(String tableName) throws Exception {
        genEntity(tableName, "cn.mulc.mulcgen");
        genMapper(tableName, "cn.mulc.mulcgen");
        genXml(tableName, "cn.mulc.mulcgen");
        genService(tableName, "cn.mulc.mulcgen");
        genServiceImpl(tableName, "cn.mulc.mulcgen");
        genController(tableName,"cn.mulc.mulcgen");
    }

    public static void main(String[] args) throws Exception {

//
//        gen("sys_config");
//        gen("user");
//        gen("sys_dict");
        List<MyClass> tableInfo = DataUtils.getTableInfo();
        for(MyClass my:tableInfo){
            gen(my.getClassName());
        }

    }
}
