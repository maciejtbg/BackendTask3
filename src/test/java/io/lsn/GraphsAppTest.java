package io.lsn;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static io.lsn.GraphsApp.getGraphFromConnections;
import static org.junit.jupiter.api.Assertions.*;

class GraphsAppTest {
    @Test
    public void shouldReturnNumber2_1() {
        Set<int[]> connections = new HashSet<>(Arrays.asList(new int[]{4, 3}, new int[]{1 ,3}, new int[]{5, 6}));
        int expected = 2;
        int result = GraphsApp.getNumberOfGraphs(getGraphFromConnections(connections));
        assertEquals(expected, result);
    }


    @Test
    public void shouldReturnNumber2_2() {
        Set<int[]> connections = new HashSet<>(Arrays.asList(new int[]{1, 2}, new int[]{2, 3}, new int[]{4 ,5}));
        int expected = 2;
        int result = GraphsApp.getNumberOfGraphs(getGraphFromConnections(connections));
        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnNumber1() {
        Set<int[]> connections = new HashSet<>(Arrays.asList(new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{4 ,5}));
        int expected = 1;
        int result = GraphsApp.getNumberOfGraphs(getGraphFromConnections(connections));
        assertEquals(expected, result);
    }

}