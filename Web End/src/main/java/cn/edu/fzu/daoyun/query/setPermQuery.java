package cn.edu.fzu.daoyun.query;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class setPermQuery {
    private Integer roleId;
    private List<Integer> perm;
}
