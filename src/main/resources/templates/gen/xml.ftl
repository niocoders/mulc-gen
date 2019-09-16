<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${myClass.moudleName}.mapper.${myClass.mapperName}">


    <!--增加${myClass.className}-->
    <insert id="insert">
        insert into
        `${myClass.tableName}`(
        <#list myClass.fieldList as field>
            <#if  myClass.fieldSize-1 gt field_index>
                `${field.fieldName}`,
            </#if>
            <#if  myClass.fieldSize-1 lte field_index>
                `${field.fieldName}`
            </#if>
        </#list>
        ) value(
        <#list myClass.fieldList as field>
            <#if  myClass.fieldSize-1 gt field_index>
                <#noparse>#{</#noparse>${field.fieldNameUpperFirstLetter}<#noparse>}</#noparse>,
            </#if>
            <#if  myClass.fieldSize-1 lte field_index>
                <#noparse>#{</#noparse>${field.fieldNameUpperFirstLetter}<#noparse>}</#noparse>
            </#if>
        </#list>

        )

    </insert>
    <!--删除${myClass.className}-->
    <delete id="delete">
        delete from `${myClass.tableName}` where id=<#noparse>#{</#noparse>id<#noparse>}</#noparse>

    </delete>
    <!--修改${myClass.className}-->
    <update id="update">
        UPDATE
        `${myClass.tableName}`
        SET
        <#list myClass.fieldList as field>
            <#if  myClass.fieldSize-1 gt field_index>
                <#noparse><if</#noparse> test="${field.fieldNameUpperFirstLetter}!=null"<#noparse>></#noparse>${field.fieldName}=<#noparse>#{</#noparse>${field.fieldNameUpperFirstLetter}<#noparse>},</#noparse></if>
            </#if>
            <#if  myClass.fieldSize-1 lte field_index>
                <#noparse><if</#noparse> test="${field.fieldNameUpperFirstLetter}!=null"<#noparse>></#noparse>${field.fieldName}=<#noparse>#{</#noparse>${field.fieldNameUpperFirstLetter}<#noparse>}</#noparse></if>
            </#if>
        </#list>
        WHERE
        id=<#noparse>#{</#noparse>Id<#noparse>}</#noparse>

    </update>
    <!-- 根据id查询${myClass.className}-->
    <select id="selectById" resultType="${myClass.moudleName}.entity.${myClass.entityName}">
        select
        <#list myClass.fieldList as field>
            <#if  myClass.fieldSize-1 gt field_index>
                ${field.fieldName} AS ${field.fieldNameUpperFirstLetter},
            </#if>
            <#if  myClass.fieldSize-1 lte field_index>
                ${field.fieldName} AS ${field.fieldNameUpperFirstLetter}
            </#if>
        </#list>
        from `${myClass.tableName}`
        where <#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>
        =<#noparse>#{</#noparse><#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list><#noparse>}</#noparse>
    </select>
    <!--  查询所有${myClass.className}-->
    <select id="selectAll" resultType="${myClass.moudleName}.entity.${myClass.entityName}">
        select
        <#list myClass.fieldList as field>
            <#if  myClass.fieldSize-1 gt field_index>
                ${field.fieldName} AS ${field.fieldNameUpperFirstLetter},
            </#if>
            <#if  myClass.fieldSize-1 lte field_index>
                ${field.fieldName} AS ${field.fieldNameUpperFirstLetter}
            </#if>
        </#list>
        from `${myClass.tableName}`
    </select>
<#--    根据用户id查找是否存在-->
<select id="selectCountById" resultType="Integer">
    select count(*) from `${myClass.tableName}`
    where <#list myClass.fieldList as field><#if field_index==0>${field.fieldName} AS ${field.fieldNameUpperFirstLetter}</#if></#list>
    =<#noparse>#{</#noparse><#list myClass.fieldList as field><#if field_index==0>${field.fieldName} AS ${field.fieldNameUpperFirstLetter}</#if></#list><#noparse>}</#noparse>
</select>

</mapper>