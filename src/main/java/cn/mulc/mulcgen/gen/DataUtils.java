package cn.mulc.mulcgen.gen;

import cn.mulc.mulcgen.vo.Field;
import cn.mulc.mulcgen.vo.MyClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: mulc-gen
 * @Package: cn.mulc.mulcgen.util
 * @ClassName: DataUtils
 * @Author: llnlg
 * @Description: 使用jdbc获取字段中的信息 sql的方式
 * @Date: 2019/9/10 17:00
 * @Version: 1.0
 */
public class DataUtils {
    //数据库链接驱动
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    //数据库host地址
    private static String HOST = "127.0.0.1";
    //数据库端口
    private static String PORT = "3306";
    //数据库名
    private static String DATABASENAME = "dradmin";
    //数据库链接URL
    private static String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASENAME + "?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8&serverTimezone=UTC";
    //数据源链接用户名
    private static String USERNAME = "root";
    //数据库链接密码
    private static String PASSWORD = "root";

    /**
     * 获取数据库链接
     *
     * @return
     */
    public static Connection init() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库链接
     *
     * @param conn
     * @throws SQLException
     */
    public static void closeConn(Connection conn) throws SQLException {
        conn.close();
    }

    /**
     * 获取数据库所有的表
     *
     * @return
     * @throws Exception
     */
    public static List<String> getTablesName() throws Exception {
        List<String> list = new ArrayList<String>();
        Connection conn = init();
        String sql = "select table_name from information_schema.tables where table_schema='" + DATABASENAME + "'";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            list.add(result.getString("TABLE_NAME"));
        }
        closeConn(conn);
        return list;
    }

    /**
     * 查询所有表的信息
     *
     * @return
     */
    public static List<MyClass> getTableInfo() throws SQLException {
        Connection conn = init();
        ArrayList<MyClass> list = new ArrayList<>();
        String sql = "SELECT table_name tableName,ENGINE,table_comment tableComment,create_time createTime FROM information_schema.TABLES WHERE table_schema = (SELECT DATABASE())";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String tableName = rs.getString("tableName");
            String tableComment = rs.getString("tableComment");
            String createTime = rs.getString("createTime");
            MyClass myClass = new MyClass();
            myClass.setClassName(tableName);
            myClass.setTableComment(tableComment);
            myClass.setLocalDate(createTime);
            list.add(myClass);
        }
        closeConn(conn);
        return list;
    }

    /**
     * 根据tableName获取类信息
     *
     * @param tableName
     * @return
     * @throws SQLException
     */
    public static MyClass getClassInfoByName(String tableName) throws SQLException {
        MyClass myClass = new MyClass();
        List<MyClass> tableInfo = getTableInfo();
        for (MyClass a : tableInfo) {
            if (a.getClassName().equals(tableName)) {
                myClass = a;
            }

        }
        return myClass;

    }

    /**
     * 根据表名查找字段信息
     *
     * @param tableName
     * @return
     */
    public static MyClass getColumnInfo(String tableName, String moudleName) throws SQLException {
        MyClass myClass = getClassInfoByName(tableName);
        ArrayList<Field> fieldList = new ArrayList<>();
        Connection conn = init();
        String sql = "SELECT column_name columnName,data_type dataType,column_comment columnComment,column_key columnKey,extra FROM information_schema.COLUMNS  WHERE table_schema = ( SELECT DATABASE ( ) ) AND table_name = '" + tableName + "'ORDER BY ordinal_position";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            //字段名
            String columnName = rs.getString("columnName");
            //数据类型
            String dataType = rs.getString("dataType");
            //数据注释
            String columnComment = rs.getString("columnComment");
            Field field = new Field();
            //将列名
            field.setFieldName(columnName);
            field.setFieldType(columnTypeToFieldType(dataType));
            field.setFieldRemarks(columnComment);
            field.setFieldNameUpperFirstLetter(upperFirstLetter(tableNameToClassName(columnName)));
            fieldList.add(field);

        }
        //将filed长度加入到myclass中
        myClass.setFieldSize(fieldList.size());
        //将表名首字母进行大写
        myClass.setClassName(upperFirstLetter(tableNameToClassName(tableName)));
        //将字段信息放入到类实体
        myClass.setFieldList(fieldList);
        //将前端传来的模块名传到类实体
        myClass.setMoudleName(moudleName);
        //关闭连接池
        closeConn(conn);

        return myClass;
    }


    //字段类型转换成属性类型
    public static String columnTypeToFieldType(String columnType) {
        String fieldType = null;
        String s = columnType.toUpperCase();
        switch (s) {
            case "INT":
                fieldType = "Integer";
                break;
            case "VARCHAR":
                fieldType = "String";
                break;
            case "CHAR":
                fieldType = "String";
                break;
            case "DATE":
                fieldType = "Date";
                break;
            case "DATETIME":
                fieldType = "Date";
                break;
            case "BIGINT":
                fieldType = "Long";
                break;
            case "DECIMAL":
                fieldType = "BigDecimal";
                break;
            case "TINYINT":
                fieldType="int";
                break;
            default:
                fieldType = "String";
                break;
        }
        return fieldType;
    }

    //首字母大写
    public static String upperFirstLetter(String src) {
        String firstLetter = src.substring(0, 1).toUpperCase();
        String otherLetters = src.substring(1);
        return firstLetter + otherLetters;
    }

    //表名转类名
    public static String tableNameToClassName(String tableName) {
        StringBuilder className = new StringBuilder();
        //aa_bb_cc  AaBbCc
        String[] split = tableName.split("_");
        for (String item : split) {
            className.append(upperFirstLetter(item));
        }
        return className.toString();


    }
}