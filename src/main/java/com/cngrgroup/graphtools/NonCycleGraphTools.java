package com.cngrgroup.graphtools;

import java.util.HashMap;

public class NonCycleGraphTools
{
    /**
     * Build a graph with given children.
     *
     * @param commits
     * @param children
     * @return
     */
    public static HashMap<String, Node> buildGraphWithChildren(String[] commits, String[][] children)
    {
        // build up a Graph, and keep a map that the key points to each node.
        HashMap<String, Node> aNodeMap = new HashMap<String, Node>();
        Node root = null;
        for (int i = 0; i < commits.length; i++)
        {
            Node commit = aNodeMap.get(commits[i]) == null ? new Node(commits[i]) : aNodeMap.get(commits[i]);
            root = (root == null) ? commit : root;
            if (children[i] != null)
            {
                for (int j = 0; j < children[i].length; j++)
                {
                    Node child = (aNodeMap.get(children[i][j]) == null) ? new Node(children[i][j]) : aNodeMap.get(
                            children[i][j]);
                    child.addParent(commit);
                    aNodeMap.put(child.getCommit(), child);
                    commit.addChild(child);
                }
            }
            aNodeMap.put(commit.getCommit(), commit);
        }
        aNodeMap.put("ROOT", root);
        return aNodeMap;
    }

    /**
     * Build a graph with parents
     *
     * @param commits
     * @param parents
     * @return
     */
    public static HashMap<String, Node> buildGraphWithParents(String[] commits, String[][] parents)
    {
        // build up a Graph, and keep a map that the key points to each node.
        HashMap<String, Node> aNodeMap = new HashMap<String, Node>();
        Node root = null;
        for (int i = 0; i < commits.length; i++)
        {
            Node commit = aNodeMap.get(commits[i]) == null ? new Node(commits[i]) : aNodeMap.get(commits[i]);
            root = (root == null) ? commit : root;
            if (parents[i] != null)
            {
                for (int j = 0; j < parents[i].length; j++)
                {
                    Node parent = (aNodeMap.get(parents[i][j]) == null) ? new Node(parents[i][j]) : aNodeMap.get(
                            parents[i][j]);
                    parent.addChild(commit);
                    aNodeMap.put(parent.getCommit(), parent);
                    commit.addParent(parent);
                }
            }
            aNodeMap.put(commit.getCommit(), commit);
        }
        aNodeMap.put("ROOT", root);
        return aNodeMap;
    }

    /**
     * Reset the graph.
     *
     * @param map
     */
    public static void resetVisited(HashMap<String, Node> map)
    {
        for (Node node : map.values())
        {
            node.setVisited(false);
        }
    }

}
