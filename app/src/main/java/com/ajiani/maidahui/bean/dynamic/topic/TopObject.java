package com.ajiani.maidahui.bean.dynamic.topic;

public class TopObject {
    public String rule;
    public String text;



    public TopObject(String rule, String text) {
        this.rule = rule;
        this.text = text;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
