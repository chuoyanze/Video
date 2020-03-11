package com.probie.video.model;

/**
 * @author ：probie
 * @date ：Created in 2020/3/6 14:55
 * @description：
 * @modified By：
 * @version: $
 */
public class Img {
    /**
     * 详情页路径
     */
    private String href;

    /**
     * 图片路径
     */
    private String src;
    /**
     * 标题
     */
    private String title;
    /**
     * 第几集
     */
    private String episode;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }
}
