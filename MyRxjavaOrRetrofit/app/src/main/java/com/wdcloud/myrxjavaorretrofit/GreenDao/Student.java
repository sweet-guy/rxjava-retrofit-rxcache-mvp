package com.wdcloud.myrxjavaorretrofit.GreenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Umbrella on 2018/12/10.
 */
@Entity
public class Student {
    @Id(autoincrement = true)
    private Long id;
    private String sNum;
    private String name;
    private String age;
    private String sex;
    @Generated(hash = 1930033953)
    public Student(Long id, String sNum, String name, String age, String sex) {
        this.id = id;
        this.sNum = sNum;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSNum() {
        return this.sNum;
    }
    public void setSNum(String sNum) {
        this.sNum = sNum;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
}
