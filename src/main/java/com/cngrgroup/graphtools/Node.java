package com.cngrgroup.graphtools;

import java.util.Set;
import java.util.HashSet;

/**
 * Data Transfer Object responsible for holding data and graph data.
 */
public class Node
{

    private String commit;
    private boolean visited = false;
    private Set<Node> children = new HashSet<Node>();
    private Set<Node> parents = new HashSet<Node>();

    public Node(String commit)
    {
        this.commit = commit;
    }

    public String getCommit()
    {
        return commit;
    }

    public void addChild(Node aNode)
    {
        children.add(aNode);
    }

    public Set<Node> getChildren()
    {
        return children;
    }

    public void addParent(Node aNode)
    {
        parents.add(aNode);
    }

    public Set<Node> getParents()
    {
        return parents;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }
}
