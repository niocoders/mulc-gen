//package cn.mulc.mulcgen.config.mybatis;
//
//import com.github.pagehelper.PageHelper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Properties;
//
///**
// * @ProjectName: mulc-gen
// * @Package: cn.mulc.mulcgen.config.mybatis
// * @ClassName: MybatisConfig
// * @Author: llnlg
// * @Description:
// * @Date: 2019/9/16 14:34
// * @Version: 1.0
// */
//@Configuration
//public class MybatisConfig {
//    //配置mybatis的分页插件pageHelper
//    @Bean
//    public PageHelper pageHelper(){
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("offsetAsPageNum","true");
//        properties.setProperty("rowBoundsWithCount","true");
//        properties.setProperty("reasonable","true");
//        properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
//        pageHelper.setProperties(properties);
//        return pageHelper;
//    }
//}
