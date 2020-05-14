package org.javaboy.vhr.model;

/**
 * 推荐关系表
 */
public class YlRecommendationRelationship {
    /** 节点ID,注册ID号;注册ID号 */
    private String nodeId ;
    /** 父节点ID,推荐ID号 */
    private String parentId ;
    /** 节点层次编号 */
    private String levelCode ;
    /** 树的深度 */
    private String levelNum ;

    public YlRecommendationRelationship(String nodeId, String parentId, String levelCode, String levelNum) {
        this.nodeId = nodeId;
        this.parentId = parentId;
        this.levelCode = levelCode;
        this.levelNum = levelNum;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(String levelNum) {
        this.levelNum = levelNum;
    }
}
