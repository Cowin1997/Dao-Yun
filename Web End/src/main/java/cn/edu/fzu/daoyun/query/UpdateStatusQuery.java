package cn.edu.fzu.daoyun.query;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UpdateStatusQuery {
    private Integer cid;
    private Boolean enabled;
}
