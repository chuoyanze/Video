package com.probie.video.model;

/**
 * @author ：probie
 * @date ：Created in 2020/3/6 14:55
 * @description：
 * @modified By：
 * @version: $
 */
public class Tame {
    private String text;
    /**
     * 右侧连接
     */
    private String href;
    private String more;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }
}
