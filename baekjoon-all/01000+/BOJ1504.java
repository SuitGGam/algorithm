/*
 * 백준 1504번 : 특정한 최단 경로
 * https://www.acmicpc.net/problem/1504
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-02-21
 * 간단 설명 : 1번 정점에서 N번 정점까지 임의의 두 정점을 통과하는 최단 거리를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BOJ1504 {
    static final int MAX = 100_000_000; // 최댓값

    static int N; // 정점의 수
    static boolean valid;
    static ArrayList<Node>[] graph; // 그래프 배열

    static class Node { // 간선 정보를 담을 class
        int idx; // 연결된 정점
        int dst; // 연결된 정점까지의 거리

        Node(int idx, int dst) {
            this.idx = idx;
            this.dst = dst;
        }
    } // Node 종료

    static int dijkstra(int start, int end) { // 최단 경로를 구하는 함수
        int[] dist = new int[N + 1]; // 거리 배열
        for (int i = 1; i <= N; i++) dist[i] = MAX; // 거리 초기화
        dist[start] = 0; // 시작점 거리 초기화

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.dst, o2.dst)); // 거리를 저장할 PriorityQueue
        pq.add(new Node(start, 0)); // 시작점 add

        while (!pq.isEmpty()) {
            Node cur = pq.poll(); // 현재 정점

            if (cur.idx == end) { // 이미 구해진 경우
                if (dist[end] == MAX) valid = false; // 유효하지 않음
                return dist[end]; // 종료
            }

            if (dist[cur.idx] < cur.dst) continue; // 더 높은 값이 들어오면 continue

            for (Node node : graph[cur.idx]) {
                int nxt = node.idx; // 다음 정점
                int nxtDst = node.dst; // 다음 정점까지의 거리

                if (dist[nxt] > cur.dst + nxtDst) {
                    dist[nxt] = cur.dst + nxtDst; // 거리값 갱신
                    pq.add(new Node(nxt, dist[nxt])); // 다음 정점 
                }
            }
        }

        if (dist[end] == MAX) valid = false; // 유효하지 않음
        return dist[end]; // 현재까지의 값 반환
    } // dijkstra 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 정점의 수
        int E = Integer.parseInt(st.nextToken()); // 간선의 수

        graph = new ArrayList[N + 1]; // 그래프 배열
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>(); // 정점 생성

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()); // a 정점
            int b = Integer.parseInt(st.nextToken()); // b 정점
            int c = Integer.parseInt(st.nextToken()); // 거리

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int v1 = Integer.parseInt(st.nextToken()); // 반드시 거쳐야 하는 정점 1
        int v2 = Integer.parseInt(st.nextToken()); // 반드시 거쳐야 하는 정점 2

        // 버퍼 닫기
        br.close();

        int path1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N); // 경로 1
        int path2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N); // 경로 2
        int minDist = Math.min(path1, path2); // 최단 경로의 길이

        // 결과값 출력하기
        System.out.print(minDist >= MAX ? -1 : minDist);
    } // main 종료
} // Main 종료