package com.cngrgroup.graphtools;

import java.util.LinkedList;
import java.util.Stack;

/**
 * SearchTools provides a set of search tools.
 *
 * @author Philip A Senger
 * @version 1.0
 * @since <pre>08/07/2012</pre>
 */
public class SearchTools
{
    /**
     * Depth first search
     *
     * @param node   a non-null node
     * @param target a non-null target
     * @param path   resulting path.
     * @return returns null for not found or the node.
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
     *
     * @param node   a non-null node
     * @param target a non-null target
     * @param path   resulting path.
     * @return returns null for not found or the node.
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

    /**
     * Lowest Common Ancestor.
     *
     * @param path1
     * @param path2
     * @return
     */
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
