package com.hl.algorithm.datastruct.graph;

import java.util.Arrays;

import org.junit.Test;


/**
 * 图结构相关常见算法
 * @author huanglin
 * @date 2024/08/19 20:50
 */
public class DoMain {

    @Test
    public void isBipartiteTest() {
        int[][] graph = new int[][]{
                {1,3},
                {0,2},
                {1,3},
                {0,2}
        };

        System.out.println(isBipartite(graph));
    }

    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        Arrays.fill(colors, -1);
        for(int i = 0; i < graph.length; i++) {
            if(colors[i] == -1  && !isBipartite(i, 0, colors, graph)) {
                return false;
            }
        }

        return true;
    }
    
    /**
     * 判断是否为二分图
     * @param curNode  当前结点
     * @param curColor 当前结点颜色
     * @param colors   结点颜色集合
     * @param graph    图集合
     * @return
     */
    private boolean isBipartite(int curNode, int curColor, int[] colors, int[][] graph) {
        if(colors[curNode] != -1) {
            return colors[curNode] == curColor;
        }

        colors[curNode] = curColor;
        for(int nextNode : graph[curNode]) {
            if(!isBipartite(nextNode, 1 - curColor, colors, graph)) {
                return false;
            }
        }

        return true;
    }
}
