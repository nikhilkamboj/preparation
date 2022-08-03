package datastructures.questions.ctci;


import java.util.List;
import java.util.Objects;

public class Vertex {
    private String name;
    private List<Vertex> connections;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vertex> getConnections() {
        return connections;
    }

    public void setConnections(List<Vertex> connections) {
        this.connections = connections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
