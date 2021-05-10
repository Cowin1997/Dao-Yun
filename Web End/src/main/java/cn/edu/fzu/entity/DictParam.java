package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class DictParam {
    @ApiModelProperty(value = "id,添加修改不需要传参")
    private Integer id;
    @ApiModelProperty(value = "字典码")
    private String code;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "详情")
    private String detail;
    @ApiModelProperty(value = "字典项数据值")
    private Integer value;
    @ApiModelProperty(value = "所属类型", hidden = true)
    private Integer type_id;

    @ApiModelProperty(value = "创建者ID")
    private Integer creatorId;
    @ApiModelProperty(value = "修改者ID")
    private Integer reviserId;
    @ApiModelProperty(value = "创建者,添加修改不需要传参")
    private String creator;
    @ApiModelProperty(value = "修改者,添加修改不需要传参")
    private String reviser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty(value = "最后修改时间,添加修改不需要传参")
    private Date last_revise_time;

    @ApiModelProperty(value = "字典项,添加字典子项时候传递到这里,添加字典父项时候不需要传参")
    private List<DictParam> subs;

}
