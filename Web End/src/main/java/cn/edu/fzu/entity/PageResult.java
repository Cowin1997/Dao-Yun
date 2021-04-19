package cn.edu.fzu.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Api
public class PageResult<T> {
    @ApiModelProperty("分页数据")
    private List<T> pageData;

    @ApiModelProperty("总条数")
    private Integer totalSize;

    @ApiModelProperty("总页数")
    private Integer totalPage;

}
