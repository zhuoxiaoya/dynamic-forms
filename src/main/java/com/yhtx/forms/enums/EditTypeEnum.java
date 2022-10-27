package com.yhtx.forms.enums;

/**
 * 组件类型枚举
 */
public enum EditTypeEnum {

    InputType(1L,"input","单行文本框"),
    TextAreaType(2L,"textArea","多行文本框"),
    NumberType(3L,"number","数字输入框"),
    SliderType(4L,"slider","滑块框"),
    DateType(5L,"date","日期选择器"),
    ChoiceType(6L,"choice","选择框"),
    TagsType(7L,"tags","标签选择器"),
    AttachmentType(8L,"attachment","附件上传"),
    MapType(9L,"map","地图控件"),
    SplitLineType(10L,"splitLine","分割线"),
    EmptyType(11L,"empty","空组件");

    private Long eid;
    private String editType;
    private String name;

    EditTypeEnum(Long eid,String editType,String name){
        this.eid = eid;
        this.editType = editType;
        this.name = name;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
