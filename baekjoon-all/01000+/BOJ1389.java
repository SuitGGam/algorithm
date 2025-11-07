/*
 * 백준 1389번 : 케빈 베이컨의 6단계 법칙
 * https://www.acmicpc.net/problem/1389
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-11-08
 * 간단 설명 : 케빈 베이컨의 수가 가장 작은 사람을 찾는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class BOJ1389 {
    
    static int kevinBacon, minSum; // 케빈 베이컨 수, 거리 합의 최솟값
    static void bfs(ArrayList<Integer>[] graph, int start, int N) { // 케빈 베이컨 수를 구하는 함수
        int[] dist = new int[N + 1]; // 거리 배열
        Arrays.fill(dist, -1); // 거리 초기화
        
        ArrayDeque<Integer> queue = new ArrayDeque<>(); // queue
        queue.add(start); // 시작점 add
        dist[start] = 0; // 시작점 거리 초기화
        
        while (!queue.isEmpty()) {
            int cur = queue.poll(); // 현재 사람
            for (int next : graph[cur]) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1; // 거리 갱신
                    queue.add(next); // 갱신한 곳 add
                }
            }
        }
        
        int sum = 0; // 거리의 합
        for (int i = 1; i <= N; i++) sum += dist[i];
        
        if (minSum > sum) {
            minSum = sum; // 거리 합의 최솟값 갱신
            kevinBacon = start; // 케빈 베이컨의 수가 가장 작은 사람 갱신
        }
    } // bfs 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken()); // 유저의 수
        int M = Integer.parseInt(st.nextToken()); // 친구 관계의 수
        
        ArrayList<Integer>[] graph = new ArrayList[N + 1]; // 친구 그래프
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            graph[B].add(A);
        }
        
        // 버퍼 닫기
        br.close();
        
        kevinBacon = -1; // 초기화
        minSum = Integer.MAX_VALUE; // 초기화
        for (int i = 1; i <= N; i++) bfs(graph, i, N);
        
        // 결과값 출력하기
        System.out.print(kevinBacon);
    } // main 종료
} // Main 종료