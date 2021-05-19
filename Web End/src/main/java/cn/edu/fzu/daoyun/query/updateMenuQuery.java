package cn.edu.fzu.daoyun.query;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class updateMenuQuery {
    private String title;
    private String icon;
    private Integer id;
    private String path;
    private Integer menu_sort;
}
