package cn.edu.fzu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CheckLog {
    private Integer id;
    private String ch_sid;
    private Integer ch_taskid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date ch_checktime;
    private Integer ch_checkscore;
    private String ch_loc;
    private String ch_info;


}
