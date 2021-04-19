package cn.edu.fzu.utils;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel
public class ListModel<T> implements Serializable {
    private T d;
}
