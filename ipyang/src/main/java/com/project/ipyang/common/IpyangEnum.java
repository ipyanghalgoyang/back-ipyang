package com.project.ipyang.common;

public class IpyangEnum {
    public enum MemberRoleType {
        ADMIN,
        USER
    }

    public enum Status {
        Y, N
    }

    public enum BoardCategory {
        INFO,
        PROMO,
        REPORT

    }

    public enum ProductType{
        FOOD,TOY,SNACK,SAND,BEAUTY,ETC
    }


    public enum NoticeCategory {
        Notice,
        Event,
        FAQ
    }

    public enum PointType {
        Charge,
        Buy,
        Sell
    }


    public enum WarningReason{
        BAD_WORDS,FRAUD,LYING

    }
    public enum LikeType{
        BOARD,COMMENT

    }



}
