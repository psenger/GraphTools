package com.cngrgroup.graphtools;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class GraphTools
{
    /**
     * Depth first search
     */
    public static Node DFS(Node node, String target, Stack<String> path)
    {
        node.setVisited(true);
        path.push(node.getCommit());
        if (target.equals(node.getCommit()))
        {
            return node;
        }
        else
        {
            Node result = null;
            for (Node child : node.getChildren())
            {
                if (!child.isVisited())
                {
                    result = DFS(child, target, path);
                }
                if (result != null)
                {
                    break;
                }
                else
                {
                    path.pop();
                }
            }
            return result;
        }
    }

    /**
     * Breath First Search
     */
    public static Node BFS(Node node, String target, Stack<String> path)
    {
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(node);
        node.setVisited(true);
        while (!queue.isEmpty())
        {
            Node currentNode = queue.poll();

            path.push(currentNode.getCommit());

            if ((target.equals(node.getCommit())))
            {
                return currentNode;
            }
            for (Node child : currentNode.getChildren())
            {
                if (!child.isVisited())
                {
                    child.setVisited(true);
                    queue.add(child);
                }
            }
        }
        return null;
    }

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

    public static void resetVisited(HashMap<String, Node> map)
    {
        for (Node node : map.values())
        {
            node.setVisited(false);
        }
    }

    public static String LCA(String[] path1, String[] path2)
    {
        String lca = null;
        String[] smallestPath = (path1.length > path2.length) ? path2 : path1;
        String[] largestPath = (path1.length > path2.length) ? path1 : path2;
        for (int i = 0; i < smallestPath.length; i++)
        {
            if ((smallestPath[i].equals(largestPath[i])))
            {
                lca = smallestPath[i];
            }
            else
            {
                break;
            }
        }
        return lca;
    }
}
