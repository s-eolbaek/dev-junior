
import CtCILibrary.AssortedMethods;

import java.util.ArrayList;
import java.util.HashSet;

/*
 2. 격자판 상의 로봇
 오른쪽,아래로만 이동할 수 있는 격자판에서 가능한 이동경로 찾기
*/
public class C802 {

    static class Point{
        int row;
        int col;
        Point(int r, int c){
            row = r;
            col = c;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }
    //내가 풀어본 방법. 일단 금지구역이 없다고 가정하고 가능한 경로를 모두 구하는 함수를 만들었는데
    //문제의 의도는 가능한 경로의 전체 좌표값을 구하는 것이었음(좌표정보를 리스트 형태로)

    //책의 해법 1. 재귀(***목적지에서 출발지로 역추적***) - maze[r][c]가 false면 금지구역
    // 시간복잡도 O(2^rc) 각 위치마다 두 가지 경우의 수가 존재하므로 n이 커질수록 소요시간이 가파르게 증가한다
    public static ArrayList<Point> getPath(boolean[][] maze){
        if(maze == null || maze.length == 0) return null;
        ArrayList<Point> path = new ArrayList<>();
        if(getPath(maze, maze.length-1, maze[0].length-1, path)) return path;

        return null;
    }

    public static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path){
        if(col < 0 || row < 0 || !maze[row][col]) return false;

        boolean isAtOrigin = (row == 0) && (col == 0);
        //인접한 지점의 경로 존재여부를 확인
        if(isAtOrigin || getPath(maze, row, col -1 , path) || getPath(maze, row -1, col, path)){
            Point p = new Point(row,col);
            path.add(p);
            return true;
        }
        return false;
    }

    //책의 해답 2. 다이나믹 프로그래밍으로 개선(한 지점을 한번씩만 방문) O(rc)
    public static ArrayList<Point> getPath2(boolean[][] maze){
        if(maze == null || maze.length == 0) return null;
        ArrayList<Point> path = new ArrayList<>();
        HashSet<Point> failedPoints = new HashSet<>();
        if(getPath2(maze, maze.length-1, maze[0].length-1, path,failedPoints)) return path;

        return null;
    }

    public static boolean getPath2(boolean[][] maze, int row, int col, ArrayList<Point> path, HashSet<Point> failedPoints){
        if(col < 0 || row < 0 || !maze[row][col]) return false;

        Point p = new Point(row,col);

        //이미 방문한 지점이면 false 리턴
        if(failedPoints.contains(p)) return false;

        boolean isAtOrigin = (row == 0) && (col == 0);
        //인접한 지점의 경로 존재여부를 확인
        if(isAtOrigin || getPath(maze, row, col -1 , path) || getPath(maze, row -1, col, path)){
            path.add(p);
            return true;
        }
        failedPoints.add(p); //캐시 저장
        return false;
    }






    public static void main(String args[]){
        int size = 5;
        boolean[][] maze = AssortedMethods.randomBooleanMatrix(size, size, 70);

        AssortedMethods.printMatrix(maze);

        ArrayList<Point> pathA = getPath(maze);
        ArrayList<Point> pathB = getPath2(maze);
        if (pathA != null) {
            System.out.println(pathA.toString());
        } else {
            System.out.println("No path found.");
        }

        if (pathB != null) {
            System.out.println(pathB.toString());
        } else {
            System.out.println("No path found.");
        }
    }
}
