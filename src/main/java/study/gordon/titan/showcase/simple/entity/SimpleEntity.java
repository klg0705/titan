package study.gordon.titan.showcase.simple.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import study.gordon.titan.common.entity.BaseEntity;

@Entity
@Table(name = "SHOWCASE_SIMPLE")
public class SimpleEntity extends BaseEntity<Long> {

    private String name;

    private Integer age;

    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "is_show")
    private Boolean show;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

}
