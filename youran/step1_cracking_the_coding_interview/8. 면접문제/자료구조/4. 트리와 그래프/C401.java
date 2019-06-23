package graph;

import java.util.LinkedList;
/*
 *  from "https://github.com/careercup/CtCI-6th-Edition"
 *
 *  방향 그래프가 주어졌을 때 두 노드 사이에 경로가 존재하는지 확인하는 알고리즘
 *
 *  BFS, DFS 모두 가능
 *  어떤 장단점이 있을지 따져보기
 *
 * */
public class C401 {

    //너비우선탐색
    public static boolean search(Graph g, Node start, Node end){
        if(start == end ) return true;

        LinkedList<Node> q = new LinkedList<>(); //Queue처럼 동작하는 연결리스트
        for(Node u : g.getNodes()){
            u.state = State.Unvisited; //모든 노드 상태 초기화
        }
        start.state = State.Visiting;
        q.add(start);
        Node u;
        while(!q.isEmpty()){
            u = q.removeFirst(); //dequeue
            if( u != null){
                for(Node v : u.getAdjacent()){
                    if(v.state  == State.Unvisited){
                        if( v == end ) return true;
                        else{
                            v.state = State.Visiting;
                            q.add(v);
                        }
                    }
                }
            }
            u.state = State.Visited;
        }
        return false;
    }

    //깊이우선탐색 (책의 예제를 참고해서 만들어봄)
    public static boolean search2(Node node, Node target){
        if(node == null ) return false;
        if(node == target ) return true;
        node.state = State.Visited;
        for(Node n : node.getAdjacent()){
            if(n.state != State.Visited ) return search2(n, target);
            else return false;
        }
        return false;
    }



    public static void main(String a[])
    {
        Graph g = createNewGraph();
        Node[] n = g.getNodes();
        Node start = n[3];
        Node end = n[5];
        System.out.println(search(g, start, end)); // d 와 f
        System.out.println(search2(start, end));
    }

    public static Graph createNewGraph()
    {
        Graph g = new Graph();
        Node[] temp = new Node[6]; //g.MAX_VERTICES

        temp[0] = new Node("a", 3);
        temp[1] = new Node("b", 0);
        temp[2] = new Node("c", 0);
        temp[3] = new Node("d", 1);
        temp[4] = new Node("e", 1);
        temp[5] = new Node("f", 0);

        // (a)-->(b)
        //    -->(c)
        //    -->(d) -->(e) --> (f)

        temp[0].addAdjacent(temp[1]);
        temp[0].addAdjacent(temp[2]);
        temp[0].addAdjacent(temp[3]);
        temp[3].addAdjacent(temp[4]);
        temp[4].addAdjacent(temp[5]);
        for (int i = 0; i < 6; i++) {
            g.addNode(temp[i]);
        }
        return g;
    }

}
