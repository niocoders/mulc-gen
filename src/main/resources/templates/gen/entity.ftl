package ${myClass.moudleName}.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.time.LocalDate;
/**
* @ClassName: ${myClass.className}
* @Description: ${myClass.tableComment}实体类
* @Date: ${myClass.localDate}
* @Author: ${myClass.author}
* @Version: 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="${myClass.className}对象", description="${myClass.tableComment}")
public class ${myClass.className} {

<#list myClass.fieldList as field>
    @ApiModelProperty(value = "${field.fieldRemarks}")
    private ${field.fieldType} ${field.fieldNameUpperFirstLetter};

</#list>
}
