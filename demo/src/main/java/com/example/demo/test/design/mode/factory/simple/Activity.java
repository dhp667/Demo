package com.example.demo.test.design.mode.factory.simple;


import org.springframework.beans.BeanUtils;

public class Activity {

    public String uid; //用户唯一ID

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private Integer awardType; //奖品类型: 1 打折券 ,2 优酷会员,3 小礼品

    private String awardNumber; //奖品编号

    public static void main(String[] args) {
        Activity activity1 = new Activity();
        Activity activity2 = new Activity();
        activity1.uid = null;
        activity2.uid = "111";
        BeanUtils.copyProperties(activity1, activity2);
        System.out.print(activity2.uid);
    }
}

class AwardInfo {
}

class DiscountInfo {

}

class YouKuInfo {

}

class SmallGift {

}