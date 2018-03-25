package com.chens.core.util;


import com.chens.core.vo.ZTree;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树工具
 */
public class TreeUtil {
  /**
   * 两层循环实现建树
   * 
   * @param treeNodes 传入的树节点列表
   * @return
   */
  public static <T extends ZTree> List<T> bulid(List<T> treeNodes, Object root) {

    List<T> trees = new ArrayList<T>();

    for (T treeNode : treeNodes) {

      if (root.equals(treeNode.getpId())) {
        trees.add(treeNode);
      }

      for (T it : treeNodes) {
        if (it.getpId().equals(treeNode.getId())) {
          if (treeNode.getChildren() == null) {
            treeNode.setChildren(new ArrayList<ZTree>());
          }
          treeNode.add(it);
        }
      }
    }
    return trees;
  }

  /**
   * 使用递归方法建树
   * 
   * @param treeNodes
   * @return
   */
  public static <T extends ZTree> List<T> buildByRecursive(List<T> treeNodes,Object root) {
    List<T> trees = new ArrayList<T>();
    for (T treeNode : treeNodes) {
      if (root.equals(treeNode.getpId())) {
        trees.add(findChildren(treeNode, treeNodes));
      }
    }
    return trees;
  }

  /**
   * 递归查找子节点
   * 
   * @param treeNodes
   * @return
   */
  public static <T extends ZTree> T findChildren(T treeNode, List<T> treeNodes) {
    for (T it : treeNodes) {
      if (treeNode.getId().equals(it.getpId())) {
        if (treeNode.getChildren() == null) {
          treeNode.setChildren(new ArrayList<ZTree>());
        }
        treeNode.add(findChildren(it, treeNodes));
      }
    }
    return treeNode;
  }

  public static <T extends ZTree> Map<String, T > getTreeMap(List<T> treeNodes)
  {
    if(CollectionUtils.isEmpty(treeNodes))
    {
      return null;
    }
    Map<String, T > map = new HashMap<String, T>();
    for (T it : treeNodes) {
      map.put(it.getId(),it);
    }
    return map;
  }


}
