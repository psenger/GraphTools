package com.cngrgroup.graphtools;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

/**
 * GraphTools Tester.
 *
 * @author Philip A Senger
 * @version 1.0
 * @since <pre>08/07/2012</pre>
 */
@RunWith(value = Parameterized.class)
public class NonCycleGraphToolsTest
{
    private String commitHashOne;
    private String commitHashTwo;
    private String expectedLowestCommonAnsetor;
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

    public NonCycleGraphToolsTest(String commitHashOne, String commitHashTwo, String expectedLowestCommonAnsetor)
    {
        this.commitHashOne = commitHashOne;
        this.commitHashTwo = commitHashTwo;
        this.expectedLowestCommonAnsetor = expectedLowestCommonAnsetor;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data()
    {
        Object[][] data = new Object[][]{
            {"A","A","A"},
            {"B","A","A"},
            {"B","B","B"},
            {"C","A","A"},
            {"C","B","B"},
            {"C","C","C"},
            {"D","A","A"},
            {"D","B","B"},
            {"D","C","C"},
            {"D","D","D"},
            {"E","A","A"},
            {"E","B","B"},
            {"E","C","C"},
            {"E","D","C"},
            {"E","E","E"},
            {"F","A","A"},
            {"F","B","B"},
            {"F","C","C"},
            {"F","D","C"},
            {"F","E","E"},
            {"F","F","F"},
            {"G","A","A"},
            {"G","B","B"},
            {"G","C","C"},
            {"G","D","C"},
            {"G","E","E"},
            {"G","F","E"},
            {"G","G","G"},
            {"H","A","A"},
            {"H","B","B"},
            {"H","C","C"},
            {"H","D","C"},
            {"H","E","E"},
            {"H","F","E"},
            {"H","G","G"},
            {"H","H","H"},
            {"I","A","A"},
            {"I","B","B"},
            {"I","C","C"},
            {"I","D","C"},
            {"I","E","E"},
            {"I","F","E"},
            {"I","G","G"},
            {"I","H","H"},
            {"I","I","I"},
            {"J","A","A"},
            {"J","B","B"},
            {"J","C","C"},
            {"J","D","D"},
            {"J","E","C"},
            {"J","F","C"},
            {"J","G","C"},
            {"J","H","C"},
            {"J","I","C"},
            {"J","J","J"},
            {"K","A","A"},
            {"K","B","B"},
            {"K","C","C"},
            {"K","D","D"},
            {"K","E","C"},
            {"K","F","C"},
            {"K","G","C"},
            {"K","H","C"},
            {"K","I","C"},
            {"K","J","J"},
            {"K","K","K"}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testLCA_Children()
    {
        HashMap<String, Node> map;
        Stack<String> pathOne;
        Stack<String> pathTwo;

        map = NonCycleGraphTools.buildGraphWithChildren(COMMITS, CHILDREN);

        pathOne = new Stack<String>();
        pathTwo = new Stack<String>();

        SearchTools.DFS(map.get("ROOT"), commitHashOne, pathOne);
        NonCycleGraphTools.resetVisited( map );
        SearchTools.DFS(map.get("ROOT"), commitHashTwo, pathTwo);

        String result = SearchTools.LCA( pathOne.toArray(new String[pathOne.size()]), pathTwo.toArray(new String[pathTwo.size()]));

        Assert.assertEquals(" Node = " + commitHashOne + " Node = " + commitHashTwo ,expectedLowestCommonAnsetor, result );
    }

    @Test
    public void testLCA_Parents()
    {
        HashMap<String, Node> map;
        Stack<String> pathOne;
        Stack<String> pathTwo;

        map = NonCycleGraphTools.buildGraphWithParents(COMMITS, PARENTS);

        pathOne = new Stack<String>();
        pathTwo = new Stack<String>();

        SearchTools.DFS(map.get("ROOT"), commitHashOne, pathOne);
        NonCycleGraphTools.resetVisited( map );
        SearchTools.DFS(map.get("ROOT"), commitHashTwo, pathTwo);

        String result = SearchTools.LCA( pathOne.toArray(new String[pathOne.size()]), pathTwo.toArray(new String[pathTwo.size()]));

        Assert.assertEquals(" Node = " + commitHashOne + " Node = " + commitHashTwo ,expectedLowestCommonAnsetor, result );
    }
}