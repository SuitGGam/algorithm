/*
 * 백준 1753번 : 최단경로
 * https://www.acmicpc.net/problem/1753
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-08-28
 * 간단 설명 : 시작점으로부터 각 정점의 최단 경로값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BOJ1753 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int V, start; // 정점의 개수, 시작 정점
    static ArrayList<Vertex>[] graph; // 그래프 정보를 담을 배열
    
    static class Vertex { // 정점을 만드는 class
        int v; // 간선 방향
        int w; // 가중치
        
        Vertex(int v, int w) {
            this.v = v;
            this.w = w;
        }
    } // Vertex 종료
    
    static int[] dist; // 최단 경로를 담을 배열
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void dijkstra() { // 최단 경로값을 구하는 함수
        PriorityQueue<Vertex> minHeap = new PriorityQueue<>((o1, o2) ->
                Integer.compare(o1.w, o2.w)); // 최솟값을 뽑아낼 PQ
        
        dist[start] = 0; // 시작점 가중치 초기화
        minHeap.add(new Vertex(start, 0)); // 시작점 정보 넣기
        
        while (!minHeap.isEmpty()) {
            Vertex curVertex = minHeap.poll(); // 현재 정점
            
            // 현재 노드의 가중치가 이전에 기록된 가중치보다 낮으면 continue
            if (dist[curVertex.v] < curVertex.w) continue;
            
            for (int i = 0 ; i < graph[curVertex.v].size(); i++) {
                Vertex nxtVertex = graph[curVertex.v].get(i); // 다음 정점
                
                if (dist[nxtVertex.v] > dist[curVertex.v] + nxtVertex.w) {
                    dist[nxtVertex.v] = dist[curVertex.v] + nxtVertex.w; // 경로값 갱신
                    
                    minHeap.add(new Vertex(nxtVertex.v, dist[nxtVertex.v])); // 다음 정점 PQ에 추가
                }
            }
        }
        
        // 결과값 추가하기
        for (int i = 1; i <= V; i++) {
            if (i == start) sb.append(0).append("\n"); // 시작점인 경우
            else {
                if (dist[i] == Integer.MAX_VALUE) sb.append("INF\n");
                else sb.append(dist[i]).append("\n");
            }
        }
    } // dijkstra 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수
        
        start = Integer.parseInt(br.readLine()); // 시작 정점
        
        graph = new ArrayList[V + 1]; // 배열 크기 지정
        for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>(); // 그래프 생성
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken()); // 정점
            int v = Integer.parseInt(st.nextToken()); // 방향
            int w = Integer.parseInt(st.nextToken()); // 가중치
            
            graph[u].add(new Vertex(v, w)); // 간선 정보 추가
        }
        
        // 버퍼 닫기
        br.close();
        
        dist = new int[V + 1]; // 배열 크기 지정
        for (int i = 1; i <= V; i++) dist[i] = Integer.MAX_VALUE; // 경로값 초기화
        dijkstra(); // 최단 경로값 구하기
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료