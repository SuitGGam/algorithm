/*
 * 백준 1707번 : 이분 그래프
 * https://www.acmicpc.net/problem/1707
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-09-30
 * 간단 설명 : 주어지는 그래프가 이분 그래프인지 아닌지 판별하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ1707 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int V; // 정점의 개수
    static boolean bl; // boolean 변수
    static ArrayList<Integer>[] graph; // 그래프 배열
    static int[] color; // 점의의 색칠 배열
    static boolean[] visited; // 방문 처리 배열
    
    static void bfs(int v, int checkNum) {
        Queue<Integer> queue = new ArrayDeque<>(); // bfs를 위한 큐
        visited[v] = true; // 방문 처리
        queue.add(v); // 원소 추가
        color[v] = checkNum; // 색칠
        
        while (!queue.isEmpty()) {
            int num = queue.poll(); // 원소 꺼내기
            for (int i : graph[num]) {
                if (visited[i]) { // 방문이 돼있는 경우
                    if (color[num] == color[i]) {
                        bl = false; // bl 갱신
                        return;
                    }
                } else { // 방문이 안 돼있는 경우
                    visited[i] = true; // 방문 처리
                    color[i] = (color[num] + 1) % 2; // 색칠
                    queue.add(i); // 원소 추가
                }
            }
            checkNum++; // 색 변환
        }
    } // bfs 종료
    
    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 추가하기 위한 객체
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken()); // 정점의 개수
            int E = Integer.parseInt(st.nextToken()); // 간선의 개수
            
            graph = new ArrayList[V + 1]; // 배열 크기 지정
            for (int j = 1; j <= V; j++) graph[j] = new ArrayList<>(); // 정점 생성
            
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(st.nextToken()); // 정점 u
                int v = Integer.parseInt(st.nextToken()); // 정점 v
                
                // 정점 연결
                graph[u].add(v);
                graph[v].add(u);
            }
            
            color = new int[V + 1]; // 배열 크기 지정
            visited = new boolean[V + 1]; // 배열 크기 지정
            
            bl = true; // bl 초기화
            
            for (int j = 1; j <= V; j++) {
                if (!bl) break;
                if (!visited[j]) bfs(j, 0);
            }
            
            // 결과값 추가하기
            if (bl) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료