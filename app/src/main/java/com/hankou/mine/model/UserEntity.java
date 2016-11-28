package com.hankou.mine.model;

/**
 * Created by bykj003 on 2016/9/5.
 */
public class UserEntity {

    public int id;

    public String name;

    public String desc;

    public String avatar;

    public UserEntity(int id, String name, String desc, String avatar) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
