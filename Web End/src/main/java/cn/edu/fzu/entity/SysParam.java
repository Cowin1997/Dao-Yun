package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class SysParam {
    @ApiModelProperty(value = "id,添加更新不需要该字段")
    private Integer id;
    @ApiModelProperty(value = "系统参数键")
    private String arg_key;
    @ApiModelProperty(value = "系统参数值")
    private String arg_value;
    @ApiModelProperty(value = "系统参数说明")
    private String arg_desc;
    @ApiModelProperty(value = "创建者ID,添加需要该字段")
    private Integer creatorId;
    @ApiModelProperty(value = "修改者ID,更新需要该字段")
    private Integer reviserId;
    @ApiModelProperty(value = "创建者,添加更新不需要该字段")
    private String creator;
    @ApiModelProperty(value = "修改者,添加更新不需要该字段")
    private String reviser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty(value = "最后修改时间,添加更新不需要该字段")
    private Date last_revise_time;
}
