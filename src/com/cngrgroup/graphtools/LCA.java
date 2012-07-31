package com.cngrgroup.graphtools;

import java.util.HashMap;
import java.util.Stack;

public class LCA
{
    /**
     * a--b--c--d--j--k
     *       \      \
     *        e--f   l
     *        \
     *         g--h--i
     */
    private static String[] COMMITS = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
    private static String[][] PARENTS = new String[][]{
            null,       //  "A"
            {"A"},      //  "B"
            {"B"},      //  "C"
            {"C"},      //  "D"
            {"C"},      //  "E"
            {"E"},      //  "F"
            {"E"},      //  "G"
            {"G"},      //  "H"
            {"H"},      //  "I"
            {"D"},      //  "J"
            {"J"},      //  "K"
            {"J"}       //  "L"
    };
    private static String[][] CHILDREN = new String[][]{
            {"B"},      //  "A"
            {"C"},      //  "B"
            {"D","E"},  //  "C"
            {"J"},      //  "D"
            {"F","G"},  //  "E"
            null,       //  "F"
            {"H"},      //  "G"
            {"I"},      //  "H"
            null,       //  "I"
            {"K","L"},  //  "J"
            null,       //  "K"
            null,       //  "L"
    };

    public static void main(String[] args)
    {
        String commitHashOne = "J";
        String commitHashTwo = "I";

        HashMap<String, Node> map = GraphTools.buildGraphWithParents(COMMITS, PARENTS);
//        HashMap<String, Node> map = GraphTools.buildGraphWithChildren(COMMITS, CHILDREN);

        Stack<String> pathOne = new Stack<String>();
        Stack<String> pathTwo = new Stack<String>();
        GraphTools.DFS(map.get("ROOT"), commitHashOne, pathOne);
        GraphTools.resetVisited( map );
        GraphTools.DFS(map.get("ROOT"), commitHashTwo, pathTwo);

        String lowestCommonAnsertor = GraphTools.LCA( pathOne.toArray(new String[pathOne.size()]), pathTwo.toArray(new String[pathTwo.size()]));
        System.out.println(lowestCommonAnsertor);
    }

}
