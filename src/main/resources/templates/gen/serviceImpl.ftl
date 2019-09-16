package ${myClass.moudleName}.service.impl;

import ${myClass.moudleName}.entity.${myClass.entityName};
import ${myClass.moudleName}.service.${myClass.serviceName};
import ${myClass.moudleName}.mapper.${myClass.mapperName};

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


/*
* @ClassName: ${myClass.className}
* @Description: ${myClass.tableComment}service实现类
* @Date: ${myClass.localDate}
* @Version: 1.0
*
*/
@Service
public class ${myClass.className} implements ${myClass.serviceName}{

    @Autowired
    private ${myClass.mapperName} ${'${myClass.mapperName}'?uncap_first};
    /**
    * 增加${myClass.tableComment}
    * @param ${'${myClass.entityName}'?uncap_first}
    * @return
    */
    @Override
    public int insert(${myClass.entityName} ${'${myClass.entityName}'?uncap_first}) {
    return ${'${myClass.mapperName}'?uncap_first}.insert(${'${myClass.entityName}'?uncap_first});
    }
    /**
    * 删除${myClass.tableComment}
    * @param <#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>
    * @return
    */
    @Override
    public int delete(<#list myClass.fieldList as field><#if field_index==0>${field.fieldType}</#if></#list> <#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>) {
    return ${'${myClass.mapperName}'?uncap_first}.delete(<#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>);
    }
    /**
    * 修改${myClass.tableComment}
    * @param ${'${myClass.entityName}'?uncap_first}
    * @return
    */
    @Override
    public int update(${myClass.entityName} ${'${myClass.entityName}'?uncap_first}){
    return ${'${myClass.mapperName}'?uncap_first}.update(${'${myClass.entityName}'?uncap_first});
    }
    /**
    * 根据id查询${myClass.tableComment}
    * @param <#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>
    * @return
    */
    @Override
    public ${myClass.entityName} selectById(<#list myClass.fieldList as field><#if field_index==0>${field.fieldType}</#if></#list> <#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>) {
    return ${'${myClass.mapperName}'?uncap_first}.selectById(<#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>);
    }
    /**
    * 查询所有${myClass.tableComment}
    * @return
    */
    @Override
    public List<${myClass.entityName}> selectAll() {
        return ${'${myClass.mapperName}'?uncap_first}.selectAll();
    }
    /**
    * 根据用户<#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>查找是否存在
    * @return
    */
    @Override
    public boolean selectCountById(<#list myClass.fieldList as field><#if field_index==0>${field.fieldType}</#if></#list> <#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>){
    if ((${'${myClass.mapperName}'?uncap_first}.selectCountById(<#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>))>=1){
    return true;
    }
    return false ;
    }
}