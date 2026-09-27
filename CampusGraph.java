import java.util.ArrayList;

public class CampusGraph {

    ArrayList<String> locations;
    ArrayList<ArrayList<String>> adjList;

    public CampusGraph() {
        locations = new ArrayList<String>();
        adjList = new ArrayList<ArrayList<String>>();
    }

    private int indexOf(String loc) {
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).equalsIgnoreCase(loc)) {
                return i;
            }
        }
        return -1;
    }

    public boolean addLocation(String loc) {
        if (indexOf(loc) != -1) {
            return false;
        }
        locations.add(loc);
        adjList.add(new ArrayList<String>());
        return true;
    }

    public boolean removeLocation(String loc) {
        int i = indexOf(loc);
        if (i == -1) return false;

        for (int j = 0; j < adjList.size(); j++) {
            adjList.get(j).remove(loc);
        }

        locations.remove(i);
        adjList.remove(i);
        return true;
    }

    public boolean addConnection(String loc1, String loc2) {
        int i1 = indexOf(loc1);
        int i2 = indexOf(loc2);
        if (i1 == -1 || i2 == -1) return false;

        if (!adjList.get(i1).contains(loc2)) adjList.get(i1).add(loc2);
        if (!adjList.get(i2).contains(loc1)) adjList.get(i2).add(loc1);
        return true;
    }

    public boolean removeConnection(String loc1, String loc2) {
        int i1 = indexOf(loc1);
        int i2 = indexOf(loc2);
        if (i1 == -1 || i2 == -1) return false;

        boolean r1 = adjList.get(i1).remove(loc2);
        boolean r2 = adjList.get(i2).remove(loc1);
        return r1 || r2;
    }

    public void displayGraph() {
        if (locations.size() == 0) {
            System.out.println("No campus locations added yet.");
            return;
        }
        System.out.println("----- Campus Network (Adjacency List) -----");
        for (int i = 0; i < locations.size(); i++) {
            System.out.print(locations.get(i) + " -> ");
            if (adjList.get(i).size() == 0) {
                System.out.println("(no connections)");
            } else {
                System.out.println(adjList.get(i));
            }
        }
    }

    public void bfs(String start) {
        int startIdx = indexOf(start);
        if (startIdx == -1) {
            System.out.println("Starting location not found.");
            return;
        }

        boolean[] visited = new boolean[locations.size()];
        ArrayList<String> queue = new ArrayList<String>();
        queue.add(start);
        visited[startIdx] = true;

        System.out.print("BFS Traversal: ");
        while (queue.size() > 0) {
            String curr = queue.remove(0);
            System.out.print(curr + " ");

            int currIdx = indexOf(curr);
            for (String nbr : adjList.get(currIdx)) {
                int nbrIdx = indexOf(nbr);
                if (!visited[nbrIdx]) {
                    visited[nbrIdx] = true;
                    queue.add(nbr);
                }
            }
        }
        System.out.println();
    }

    public void dfs(String start) {
        int startIdx = indexOf(start);
        if (startIdx == -1) {
            System.out.println("Starting location not found.");
            return;
        }

        boolean[] visited = new boolean[locations.size()];
        System.out.print("DFS Traversal: ");
        dfsVisit(start, visited);
        System.out.println();
    }

    private void dfsVisit(String curr, boolean[] visited) {
        int currIdx = indexOf(curr);
        visited[currIdx] = true;
        System.out.print(curr + " ");

        for (String nbr : adjList.get(currIdx)) {
            int nbrIdx = indexOf(nbr);
            if (!visited[nbrIdx]) {
                dfsVisit(nbr, visited);
            }
        }
    }
}
