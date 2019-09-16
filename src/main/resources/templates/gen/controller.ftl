package ${myClass.moudleName}.controller;
import  ${myClass.moudleName}.vo.PageRequest;
import ${myClass.moudleName}.entity.${myClass.entityName};
import ${myClass.moudleName}.service.${myClass.serviceName};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
/**
* @ClassName: ${myClass.className}
* @Description: ${myClass.tableComment}controller类
* @Date: ${myClass.localDate}
* @Author: ${myClass.author}
* @Version: 1.0
*/
@Api(tags = {"${myClass.tableComment}"})
@RequestMapping("/${myClass.tableName}")
@RestController
public class ${myClass.className} {
    @Autowired
    private ${myClass.serviceName} ${'${myClass.serviceName}'?uncap_first};

    /**
     * 新增${myClass.tableComment}
     *
     * @param ${'${myClass.entityName}'?uncap_first}
     * @return
     */
    @ApiOperation(value = "新增${myClass.tableComment}")
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody ${myClass.entityName} ${'${myClass.entityName}'?uncap_first}) {
     return new ResponseEntity(${'${myClass.serviceName}'?uncap_first}.insert(${'${myClass.entityName} '?uncap_first}), HttpStatus.OK);
    }

    /**
     * 删除${myClass.tableComment}
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除${myClass.tableComment}")
    @DeleteMapping("/delete/{<#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>}")
    public ResponseEntity delete(@PathVariable <#list myClass.fieldList as field><#if field_index==0>${field.fieldType}</#if></#list> <#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>) {
     return new ResponseEntity(${'${myClass.serviceName}'?uncap_first}.delete(<#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>), HttpStatus.OK);
    }
    /**
     * 根据id修改${myClass.tableComment}
     *
     * @param ${'${myClass.entityName}'?uncap_first}
     * @return
    */
    @ApiOperation(value = "根据id修改${myClass.tableComment}")
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ${myClass.entityName} ${'${myClass.entityName}'?uncap_first}) {
     return new ResponseEntity(${'${myClass.serviceName}'?uncap_first}.update(${'${myClass.entityName}'?uncap_first}), HttpStatus.OK);
    }


    /**
     * 根据<#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>查找${myClass.tableComment}
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据<#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>查找${myClass.tableComment}")
    @GetMapping("/selectById/{<#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>}")
    public ResponseEntity selectById(<#list myClass.fieldList as field><#if field_index==0>${field.fieldType}</#if></#list> <#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>) {
     return new ResponseEntity(${'${myClass.serviceName}'?uncap_first}.selectById(<#list myClass.fieldList as field><#if field_index==0>${field.fieldName}</#if></#list>), HttpStatus.OK);

    }

    /**
     * 查找所有${myClass.tableComment}
     *
     * @return
     */
    @ApiOperation(value = "查找所有${myClass.tableComment}")
    @GetMapping("/selectAll")
    public ResponseEntity selectAll() {
     return new ResponseEntity(${'${myClass.serviceName}'?uncap_first}.selectAll(), HttpStatus.OK);
    }




}
