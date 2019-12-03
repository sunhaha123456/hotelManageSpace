package com.rose.data.to.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能：用户信息主要指：token、用户状态
 * @author sunpeng
 * @date 2018
 */
@Data
public class UserRedisVo implements Serializable {
    // 用户token
    private String token;

    // 用户可访问后台url
    private List<String> bgUrlList;

    public UserRedisVo() {
    }

    public UserRedisVo(String token, List<String> bgUrlList) {
        this.token = token;
        this.bgUrlList = bgUrlList;
    }
}