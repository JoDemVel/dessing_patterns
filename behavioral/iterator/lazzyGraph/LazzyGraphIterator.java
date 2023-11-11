package behavioral.iterator.lazzyGraph;

import java.util.ArrayList;
import java.util.Stack;

interface GraphCollection {
    Iterator getIterator(int vertex);
    void addEdge(int from, int to);
    int getVertexCount();
    ArrayList<Integer> getAdjList(int vertex);
}

interface Iterator {
    boolean hasNext();
    Object next();
}

class DFSIterator implements Iterator {
    private GraphCollection collection;
    private Stack<Integer> stack;
    private boolean visited[];

    public DFSIterator(GraphCollection collection, int startVertex) {
        this.collection = collection;
        stack = new Stack<>();
        visited = new boolean[collection.getVertexCount()];
        stack.push(startVertex);
    }

    @Override
    public boolean hasNext() {
        return !stack.empty();
    }

    @Override
    public Object next() {
        if(!hasNext()) {
            return null;
        }
        int vertex = stack.pop();
        ArrayList<Integer> adjList = collection.getAdjList(vertex);
        for (int i = 0; i < adjList.size(); i++) {
            int adjVertex = adjList.get(i);
            if (!visited[adjVertex]) {
                stack.push(adjVertex);
                visited[adjVertex] = true;
            }
        }
        return vertex;
    }
}

//It would be better if you inject the Iterator
class Graph implements GraphCollection {
    private ArrayList<Integer> adjList[];

    public Graph(int vertex) {
        adjList = new ArrayList[vertex];
        for (int i = 0; i < vertex; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to) {
        adjList[from].add(to);
    }

    public ArrayList<Integer>[] getAdjList() {
        return adjList;
    }

    public ArrayList<Integer> getAdjList(int vertex) {
        return adjList[vertex];
    }

    public int getVertexCount() {
        return adjList.length;
    }

    public Iterator getIterator(int vertex) {
        return new DFSIterator(this, vertex);
    }
}


public class LazzyGraphIterator {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);

        Iterator iterator = graph.getIterator(0);
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}

