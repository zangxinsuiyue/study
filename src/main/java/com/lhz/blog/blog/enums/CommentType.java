package com.lhz.blog.blog.enums;

/**
 * @author Administrator
 */

public enum  CommentType {
    /**
     * LEVEL_ONE_COMMENT为一级评论，针对问题的
     * LEVEL_TWO_COMMENT为二级评论，针对评论的
     * */
    LEVEL_ONE_COMMENT(1),
    LEVEL_TWO_COMMENT(2);

    private final Integer level;
    CommentType(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    /**
     * 通过将传入的等级与枚举中的等级一一对比，若有相等的则返回true，否则返回false
     * */
    public static boolean isExist(Integer level) {
        for (CommentType type : CommentType.values()) {
            if(type.getLevel().equals(level)){
                return true;
            }
        }
        return false;
    }
}
