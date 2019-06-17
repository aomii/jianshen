package com.rabbit.fitness.utils;

import com.rabbit.fitness.gym.common.TreeNode;
import com.rabbit.fitness.pojo.Auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtils {

    public static List<TreeNode> bulidCheckedTree(List<Auth> list, String authIds, Integer parentId){
        List<TreeNode> nodeList = new ArrayList();
        for(Auth auth : list){
            if(auth.getParentId().equals(parentId)){
                TreeNode node = new TreeNode();
                node.setId(auth.getAuthId());
                node.setIconCls(auth.getIconCls());
                node.setState(auth.getState());
                node.setText(auth.getAuthName());
                if(auth.getAuthPath() != null && !"".equals(auth.getAuthPath())){
                    Map attr = new HashMap<>();
                    attr.put("authPath",auth.getAuthPath());
                    node.setAttributes(attr);
                }
                if(authIds!=null&&!"".equals(authIds)){
                    String[] arr = authIds.split(",");
                    for(String s: arr){
                        if(auth.getAuthId().equals(Integer.parseInt(s))){
                            node.setChecked(true);
                        }
                    }
                }
                node.setChildren(bulidCheckedTree(list,authIds,auth.getAuthId()));
                nodeList.add(node);
            }


        }
        return nodeList;
    }

    public static List<Auth> buildGridTree(List<Auth> list,Integer parentId){
        List nodeList = new ArrayList();
        for(Auth auth : list){
            if(auth.getParentId().equals(parentId)){
                Auth node = new Auth();
                node.setAuthDescription(auth.getAuthDescription());
                node.setAuthId(auth.getAuthId());
                node.setAuthName(auth.getAuthName());
                node.setAuthPath(auth.getAuthPath());
                node.setIconCls(auth.getIconCls());
                node.setParentId(auth.getParentId());
                node.setChildren(buildGridTree(list,node.getAuthId()));
                nodeList.add(node);
            }
        }
        return nodeList;
    }

}
