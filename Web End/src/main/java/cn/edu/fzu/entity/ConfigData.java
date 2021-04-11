package cn.edu.fzu.entity;

public class ConfigData {
    private Integer id;
    private Integer code;
    private String key;
    private String value;
    private String info;


    @Override
    public String toString() {
        return "ConfigData{" +
                "id=" + id +
                ", code=" + code +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ConfigData(Integer id, Integer code, String key, String value) {
        this.id = id;
        this.code = code;
        this.key = key;
        this.value = value;
    }
}
