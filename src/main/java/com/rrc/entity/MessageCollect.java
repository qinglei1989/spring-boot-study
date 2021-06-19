package com.rrc.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.INTERNAL;

@Data
@NoArgsConstructor
public class MessageCollect implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 收藏数据标识
     */
    private String id;
    /**
     * 收藏用户标识
     */
    private String userId;
    /**
     * 收藏用户名字
     */
    private String userName;
    /**
     * 收藏数据名称
     */
    private String title;
    /**
     * 收藏目标用户
     */
    private String toUserId;

    /**
     * @Author Wangql
     * @Description //TODO
     * @Date 16:37 2021/6/19
     * @Param
     * @return
     **/
    private List<String> tt;
    /**
     *
     * @param id
     *            收藏数据标识
     * @param userId
     *            收藏用户标识
     * @param userName
     *            收藏用户名字
     * @param title
     *            收藏数据名称
     * @param toUserId
     *            收藏目标用户
     */
    public MessageCollect(String id, String userId, String userName, String title, String toUserId, List<String> tt) {
        super();
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.toUserId = toUserId;
        this.tt = tt;
    }
}
