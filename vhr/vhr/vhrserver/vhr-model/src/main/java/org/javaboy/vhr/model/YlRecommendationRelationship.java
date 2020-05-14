package org.javaboy.vhr.model;
/**
        * @author jianfengLIan
        * @time 2020年5月13日14:45:07
        * @deprecated 推荐关系模型
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

    public YlRecommendationRelationship() {
    }

    public YlRecommendationRelationship(String nodeId, String parentId, String levelCode, String levelNum) {
        this.nodeId = nodeId;
        this.parentId = parentId;
        this.levelCode = levelCode;
        this.levelNum = levelNum;
    }

    /** 节点ID,注册ID号;注册ID号 */
    public String getNodeId(){
        return this.nodeId;
    }
    /** 节点ID,注册ID号;注册ID号 */
    public void setNodeId(String nodeId){
        this.nodeId = nodeId;
    }
    /** 父节点ID,推荐ID号 */
    public String getParentId(){
        return this.parentId;
    }
    /** 父节点ID,推荐ID号 */
    public void setParentId(String parentId){
        this.parentId = parentId;
    }
    /** 节点层次编号 */
    public String getLevelCode(){
        return this.levelCode;
    }
    /** 节点层次编号 */
    public void setLevelCode(String levelCode){
        this.levelCode = levelCode;
    }
    /** 树的深度 */
    public String getLevelNum(){
        return this.levelNum;
    }
    /** 树的深度 */
    public void setLevelNum(String levelNum){
        this.levelNum = levelNum;
    }
}
