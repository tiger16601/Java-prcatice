package domain;

import java.io.Serial;
import java.io.Serializable;

public class Daily implements Serializable {
    @Serial
    private static final long serialVersionUID = -1565514926281335942L;
    private String id;
    private String title;
    private String content;

    public Daily() {
    }

    public Daily(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    /**
     * 获取
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return this.id + "-" + this.getTitle() + "-" + this.getContent();
    }
}
