/*
 * 백준 14588번 : Line Friends (Small)
 * https://www.acmicpc.net/problem/14588
 * 난이도 : 골드 5
 * 풀이 날짜 : 2026-03-21
 * 간단 설명 : 친구 사이의 관계가 얼마나 가까운지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class BOJ14588 {
    static int N; // 친구의 수
    static int[][] range; // 범위 배열
    static ArrayList<Integer>[] friend; // 친구 관계 배열

    static int bfs(int start, int target) { // 가까운 정도를 찾을 함수
        boolean[] visited = new boolean[N + 1]; // 방문 처리 배열
        ArrayDeque<int[]> queue = new ArrayDeque<>(); // 친구 번호를 넣을 Queue

        queue.add(new int[] {start, 0}); // 시작점 add
        visited[start] = true; // 시작점 방문 처리
        while (!queue.isEmpty()) {
            int[] cur = queue.poll(); // 현재 친구

            for (int nxt : friend[cur[0]]) {
                if (nxt == target) return cur[1] + 1; // 목표를 찾으면 가까운 정도 반환

                if (visited[nxt]) continue; // 방문한 곳이면 continue

                visited[nxt] = true; // 방문 처리
                queue.add(new int[] {nxt, cur[1] + 1}); // 다음 지점 add
            }
        }

        return -1; // 가까운 정도 반환
    } // bfs 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 친구의 수
        friend = new ArrayList[N + 1]; // 친구 관계 배열
        for (int i = 1; i <= N; i++) friend[i] = new ArrayList<>(); // 관계 생성

        range = new int[N + 1][2]; // 범위 배열
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            range[i][0] = Integer.parseInt(st.nextToken()); // 시작 범위
            range[i][1] = Integer.parseInt(st.nextToken()); // 끝 범위

            for (int j = 1; j < i; j++) {
                if (Math.max(range[i][0], range[j][0]) <= Math.min(range[i][1], range[j][1])) {
                    // 관계 연결
                    friend[i].add(j);
                    friend[j].add(i);
                }
            }
        }

        StringBuilder sb = new StringBuilder(); // 가까운 정도를 저장할 객체
        int Q = Integer.parseInt(br.readLine()); // 질문의 개수
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int f1 = Integer.parseInt(st.nextToken()); // 친구 1
            int f2 = Integer.parseInt(st.nextToken()); // 친구 2

            sb.append(bfs(f1, f2)).append("\n"); // 결과값 추가하기
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료