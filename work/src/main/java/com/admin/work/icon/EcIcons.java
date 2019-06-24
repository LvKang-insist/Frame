package com.admin.work.icon;

import com.joanzapata.iconify.Icon;

/**
 * Copyright (C)
 * 文件名称: EcIcons
 * 创建人: 345
 * 创建时间: 2019/4/14 22:02
 * 描述: ${DESCRIPTION}
 */
public enum EcIcons implements Icon {

    /**
     *
     */
    icon_ali_pay('\ue694'),
    icon_scan('\ue60b');


    private char character;
    EcIcons(char character) {
        this.character = character;
    }

    //图标的键，例如'fa-ok'
    @Override
    public String key() {
        return name().replace('_', '-');
    }

    //与字体中的键匹配的字符
    @Override
    public char character() {
        return character;
    }
}
