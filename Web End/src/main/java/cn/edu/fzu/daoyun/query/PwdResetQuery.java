package cn.edu.fzu.daoyun.query;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class PwdResetQuery {
    private Integer uid;
    private String oldPass;
    private String newPass;
}
