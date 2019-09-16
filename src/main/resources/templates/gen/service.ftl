package ${myClass.moudleName}.service;

import ${myClass.moudleName}.entity.${myClass.entityName};
import java.util.List;
/*
* @ClassName: ${myClass.className}
* @Description: ${myClass.tableComment}service接口
* @Date: ${myClass.localDate}
* @Version: 1.0
*
*/
public interface ${myClass.className} {

        /**
        * 增加${myClass.tableComment}
        * @param ${myClass.entityName}
        * @return
        */
        int insert(${myClass.entityName} ${myClass.entityName});

        /**
        * 删除${myClass.tableComment}
        * @param <#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>
        * @return
        */
        int delete(<#list myClass.fieldList as field><#if field_index==0>${field.fieldType}</#if></#list> <#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>);

        /**
        * 修改${myClass.tableComment}
        * @param ${'${myClass.entityName}'?uncap_first}
        * @return
        */
        int update(${myClass.entityName} ${'${myClass.entityName}'?uncap_first});
        /**
        * 根据id查询${myClass.tableComment}
        * @param <#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>
        * @return
        */
        ${myClass.entityName} selectById(<#list myClass.fieldList as field><#if field_index==0>${field.fieldType}</#if></#list> <#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>);

        /**
        * 查询所有${myClass.tableComment}
        * @return
        */
        List <${myClass.entityName}> selectAll();
        /**
        * 根据用户<#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>查找是否存在
        * @return
        */
        boolean selectCountById(<#list myClass.fieldList as field><#if field_index==0>${field.fieldType}</#if></#list> <#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>);
        }