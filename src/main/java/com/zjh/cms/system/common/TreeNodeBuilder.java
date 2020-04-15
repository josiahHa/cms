package com.zjh.cms.system.common;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {

    /*
     * 把没有层级关系的变为有层级关系
     */
    public static List<TreeNode> build(List<TreeNode> treeNodes,Integer topId){
        List<TreeNode> nodes = new ArrayList<>();
        for (TreeNode treeNode:treeNodes){
            Integer id = treeNode.getId();
            Integer pid = treeNode.getPid();
            if(topId == pid ){
                List<TreeNode> c_node = build(treeNodes,id);
                treeNode.setChildren(c_node);
                nodes.add(treeNode);
            }
        }
        return nodes;
    }

}
