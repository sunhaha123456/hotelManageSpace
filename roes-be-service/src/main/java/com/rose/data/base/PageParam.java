package com.rose.data.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageParam implements Serializable{
    private Integer page; //第几页
    private Integer rows; //每页多少条记录
}