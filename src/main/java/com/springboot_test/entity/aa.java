package com.springboot_test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;

@ApiModel(value="com.springboot_test.entity.aa")
@Table(name = "`aa`")
public class aa implements Serializable {
    @Id
    @Column(name = "`id`")
    @ApiModelProperty(value="id")
    private Integer id;

    @Column(name = "`a`")
    @ApiModelProperty(value="a")
    private String a;

    @Column(name = "`b`")
    @ApiModelProperty(value="b")
    private String b;

    @Column(name = "`c`")
    @ApiModelProperty(value="c")
    private Byte c;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return a
     */
    public String getA() {
        return a;
    }

    /**
     * @param a
     */
    public void setA(String a) {
        this.a = a == null ? null : a.trim();
    }

    /**
     * @return b
     */
    public String getB() {
        return b;
    }

    /**
     * @param b
     */
    public void setB(String b) {
        this.b = b == null ? null : b.trim();
    }

    /**
     * @return c
     */
    public Byte getC() {
        return c;
    }

    /**
     * @param c
     */
    public void setC(Byte c) {
        this.c = c;
    }
}