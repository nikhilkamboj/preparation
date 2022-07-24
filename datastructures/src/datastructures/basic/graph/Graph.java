package datastructures.basic.graph;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Graph {
    // ArrayList of heads, then linked list containing value/location of each vertex.
    Map<Vertex, List<Vertex>> adjVertexes = new HashMap<>();

    // Simply adding vertexes, no edge connection as of now
    public void addVertex(Integer vertex) {
        adjVertexes.putIfAbsent(new Vertex(vertex), new ArrayList<>());
    }

    // Since we are using non directional graph, therefore adding connection for each vertex
    public void addEdge(Integer vertex1, Integer vertex2) {
        Vertex v1 = new Vertex(vertex1);
        Vertex v2 = new Vertex(vertex2);

        adjVertexes.get(v1).add(v2);
        adjVertexes.get(v2).add(v1);
    }

    public void removeVertex(Integer value) {
        Vertex vertex = new Vertex(value);
        List<Vertex> vertexList = adjVertexes.get(vertex);
        adjVertexes.remove(vertex);
        // for each vertex, remove given vertex from the list
        for (Vertex v: vertexList) {
            List<Vertex> vList = adjVertexes.get(v);
            vList.remove(vertex);
        }
    }

    public void removeEdge(Integer v1, Integer v2) {
        Vertex vertex1 = new Vertex(v1);
        Vertex vertex2 = new Vertex(v2);
        // null check can be added if we want to handle null pointer if edge present
        adjVertexes.get(vertex1).remove(vertex2);
        adjVertexes.get(vertex2).remove(vertex1);
    }

    public List<Vertex> findAdjacentVertexes(Integer v) {
        Vertex vertex = new Vertex(v);
        return adjVertexes.get(vertex);
    }

    // GRAPH TRAVERSAL
    // DFS
    public Set<Vertex> depthFirstSearch(Graph graph, Integer root) {
        Vertex rootVertex = new Vertex(root);
        Stack<Vertex> vertexStack = new Stack<>();
        Set<Vertex> visitedSet = new LinkedHashSet<>();
        vertexStack.push(rootVertex);

        while (!vertexStack.isEmpty()) {
            Vertex vertex = vertexStack.pop();
            if (!visitedSet.contains(vertex)) {
                visitedSet.add(vertex);
                for (Vertex v: graph.adjVertexes.get(vertex)) {
                    vertexStack.push(v);
                }
            }
        }
        return visitedSet;
    }


    // BFS
    public Set<Vertex> breadthFirstSearch(Graph graph, Integer root) {
        Vertex rootVertex = new Vertex(root);
        Queue<Vertex> vertexQueue = new LinkedList<>();
        Set<Vertex> visitedSet = new LinkedHashSet<>();
        vertexQueue.add(rootVertex);
        visitedSet.add(rootVertex);
        while (!vertexQueue.isEmpty()) {
            Vertex vertex = vertexQueue.poll();
            for (Vertex v: graph.adjVertexes.get(vertex)) {
                if (!visitedSet.contains(v)) {
                    vertexQueue.add(v);
                    visitedSet.add(v);
                }
            }
        }
        return visitedSet;
    }

}
