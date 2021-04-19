package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class DictParam {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "字典码")
    private String code;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "详情")
    private String detail;
    @ApiModelProperty(value = "字典项数据值")
    private Integer value;
    @ApiModelProperty(value = "所属类型",hidden = true)
    private Integer type_id;
    @ApiModelProperty(value = "字典项")
    private List<DictParam> subs;

}
