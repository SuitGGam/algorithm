/*
 * 백준 1167번 : 트리의 지름
 * https://www.acmicpc.net/problem/1167
 * 난이도 : 골드 2
 * 풀이 날짜 : 2025-10-05
 * 간단 설명 : 임의의 두 정점 사이의 거리 중 가장 긴 거리를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ1167 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static class Vertex { // 정점을 만드는 클래스
        int edge;
        int distance;
        
        Vertex(int edge, int distance) {
            this.edge = edge;
            this.distance = distance;
        }
    } // Vertex 종료
    
    static ArrayList<Vertex>[] tree; // 트리 배열
    static boolean[] visited; // 방문 처리 배열
    static int maxDistance; // 최장 거리
    static int farthestVertex; // 가장 멀리 있는 정점
    
    static void dfs(int edge, int distance) { // 트리 dfs 함수
        visited[edge] = true; // 방문 처리
        if (maxDistance < distance) { // 최장 거리인 경우
            maxDistance = distance; // 최장 거리 갱신
            farthestVertex = edge;  // 가장 먼 노드 갱신
        }
        
        for (Vertex v : tree[edge]) {
            if (!visited[v.edge]) dfs(v.edge, distance + v.distance);
        }
    } // dfs 종료
    
    public static void main(String[] args) throws IOException {
        int V = Integer.parseInt(br.readLine()); // 정점의 개수
        tree = new ArrayList[V + 1]; // 트리 배열
        for (int i = 1; i <= V; i++) tree[i] = new ArrayList<>(); // 정점 생성
        
        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken()); // 정점 번호
            while (true) {
                int e = Integer.parseInt(st.nextToken()); // 이어진 정점 번호
                
                // 종료 조건
                if (e == -1) break;
                
                int distance = Integer.parseInt(st.nextToken()); // v정점과 e정점 사이의 거리
                tree[v].add(new Vertex(e, distance)); // 정점 연결
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        // 가장 먼 노드 구하기
        maxDistance = 0; // 최장 거리 초기화
        farthestVertex = 0; // 가장 먼 노드 초기화
        visited = new boolean[V + 1]; // 배열 크기 지정
        dfs(1, 0);
        
        // 가장 먼 노드에서 최장 거리 구하기
        maxDistance = 0; // 최장 거리 초기화
        visited = new boolean[V + 1]; // 방문 배열 재생성
        dfs(farthestVertex, 0);
        
        // 결과값 출력하기
        System.out.print(maxDistance);
    } // main 종료
} // Main 종료