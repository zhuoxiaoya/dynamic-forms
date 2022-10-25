package com.yhtx.forms.enums;

/**
 * 组件类型枚举
 */
public enum EditTypeEnum {

    InputType(1,"input","单行文本框"),
    TextAreaType(2,"textArea","多行文本框"),
    NumberType(3,"number","数字输入框"),
    SliderType(4,"slider","滑块框"),
    DateType(5,"date","日期选择器"),
    ChoiceType(6,"choice","选择框"),
    TagsType(7,"tags","标签选择器"),
    AttachmentType(8,"attachment","附件上传"),
    MapType(9,"map","地图控件"),
    SplitLineType(10,"splitLine","分割线"),
    EmptyType(11,"empty","空组件");

    private int eid;
    private String editType;
    private String name;

    EditTypeEnum(int eid,String editType,String name){
        this.eid = eid;
        this.editType = editType;
        this.name = name;
    }
}
