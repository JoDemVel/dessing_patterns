package behavioral.iterator.graph;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

class Node<T> {
    private T              value;
    private List<Node<T>>  neighbors;

    public Node(T value) {
        this.value = value;
        this.neighbors = new ArrayList<Node<T>>();
    }

    public T getValue() {
        return value;
    }

    public void addNeighbor(Node<T> neighbor) {
        neighbors.add(neighbor);
    }

    public List<Node<T>> getNeighbors() {
        return neighbors;
    }
}

interface GraphCollection<T> {
    Iterator<T> getIterator();
    void addEdge(T from, T to);
    int getVertexCount();
    List<Node<T>> getNeighbors(T node);
    Node<T> getNode(T node);
}

interface Iterator<T> {
    boolean hasNext();
    Node<T> next();
}

class Graph<T> implements GraphCollection<T> {
    private Map<T, Node<T>> nodes;
    private Iterator<T> iterator;

    public Graph() {
        nodes = new HashMap<>();
    }

    public Iterator<T> getIterator() {
        return iterator;
    }

    public void setIterator(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    public List<Node<T>> getNeighbors(T node) {
        return nodes.get(node).getNeighbors();
    }

    public Node<T> getNode(T node) {
        return nodes.get(node);
    }

    public void addEdge(T from, T to) {
        Node<T> fromNode = nodes.get(from);
        Node<T> toNode = nodes.get(to);

        if(fromNode != null && toNode != null) {
            fromNode.addNeighbor(toNode);
            toNode.addNeighbor(fromNode);
            return;
        }

        if(fromNode == null && toNode == null) {
            fromNode = new Node<T>(from);
            toNode = new Node<T>(to);

            fromNode.addNeighbor(toNode);
            toNode.addNeighbor(fromNode);

            nodes.put(from, fromNode);
            nodes.put(to, toNode);
            return;
        }

        if(fromNode == null) {
            fromNode = new Node<T>(from);
            fromNode.addNeighbor(toNode);

            toNode.addNeighbor(fromNode);

            nodes.put(from, fromNode);
            return;
        }

        if(toNode == null) {
            toNode = new Node<T>(to);

            fromNode.addNeighbor(toNode);
            toNode.addNeighbor(fromNode);

            nodes.put(to, toNode);
            return;
        }
    }

    public int getVertexCount() {
        return nodes.size();
    }
}

class DFSIterator<T> implements Iterator<T> {
    private Stack<Node<T>> stack;
    private Set<Node<T>> visited;

    public DFSIterator(GraphCollection<T> collection, T startNode) {
        Node<T> node = collection.getNode(startNode);
        stack = new Stack<Node<T>>();
        visited = new HashSet<Node<T>>();
        stack.push(node);
    }

    @Override
    public boolean hasNext() {
        return !stack.empty();
    }

    @Override
    public Node<T> next() {
        if(!hasNext()) {
            return null;
        }
        Node<T> node = stack.pop();
        visited.add(node);

        for (Node<T> neighbor : node.getNeighbors()) {
            if (!visited.contains(neighbor)) {
                stack.push(neighbor);
            }
        }
        return node;
    }
}

class BFSIterator<T> implements Iterator<T> {
    private Queue<Node<T>> queue;
    private Set<Node<T>> visited;

    public BFSIterator(GraphCollection<T> collection, T startNode) {
        Node<T> node = collection.getNode(startNode);
        queue = new LinkedList<Node<T>>();
        visited = new HashSet<Node<T>>();
        queue.add(node);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Node<T> next() {
        if(!hasNext()) {
            return null;
        }
        Node<T> node = queue.poll();
        visited.add(node);

        for (Node<T> neighbor : node.getNeighbors()) {
            if (!visited.contains(neighbor)) {
                queue.add(neighbor);
            }
        }
        return node;
    }
}

public class GraphIterator {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<String>();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");

        graph.setIterator(new DFSIterator<String>(graph, "A"));

        Iterator<String> iterator = graph.getIterator();
        while(iterator.hasNext()) {
            System.out.print(iterator.next().getValue() + " ");
        }

        System.out.println();

        graph.setIterator(new BFSIterator<String>(graph, "A"));

        iterator = graph.getIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().getValue() + " ");
        }
    }
}
