/*
 * 백준 1325번 : 효율적인 해킹
 * https://www.acmicpc.net/problem/1325
 * 난이도 : 실버 1
 * 풀이 날짜 : 2026-04-03
 * 간단 설명 : 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터 번호를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Arrays;

public class BOJ1325 {
    static int N; // 컴퓨터의 수
    static int max; // 최대 감염 컴퓨터 수
    static ArrayList<Integer>[] relation; // 관계 배열
    static int[] result; // 결과 배열
    static boolean[] visited; // 방문 처리 배열

    static void bfs(int start) { // 해킹시키는 함수
        ArrayDeque<Integer> queue = new ArrayDeque<>(); // 다음 감염시킬 컴퓨터 번호를 담을 Queue

        queue.add(start); // 시작점 add
        visited[start] = true; // 시작점 방문 처리

        int cnt = 0; // 감염시킨 컴퓨터의 수
        while (!queue.isEmpty()) {
            int cur = queue.poll(); // 현재 컴퓨터

            for (int nxt : relation[cur]) {
                if (!visited[nxt]) {
                    queue.add(nxt); // 다음 컴퓨터 add
                    visited[nxt] = true; // 다음 컴퓨터 방문 처리
                    cnt++; // 감염 컴퓨터 증가
                }
            }
        }

        result[start] = cnt; // 감염시킨 컴퓨터의 수
        max = Math.max(max, result[start]); // 최대 감염 컴퓨터 수 갱신
    } // bfs 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 컴퓨터의 수
        int M = Integer.parseInt(st.nextToken()); // 신뢰하는 관계의 수

        relation = new ArrayList[N + 1]; // 관계 배열
        for (int i = 1; i <= N; i++) relation[i] = new ArrayList<>(); // 컴퓨터 생성

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int u = Integer.parseInt(st.nextToken()); // 컴퓨터 1
            int v = Integer.parseInt(st.nextToken()); // 컴퓨터 2

            relation[v].add(u); // 신뢰 관계 형성
        }

        // 버퍼 닫기
        br.close();

        result = new int[N + 1]; // 결과 배열
        max = 0; // 최대 해킹 가능한 컴퓨터의 수

        visited = new boolean[N + 1]; // 방문 처리 배열
        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited, false); // 방문 처리 배열 초기화
            bfs(i); // 해킹시키기
        }

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 1; i <= N; i++) if (result[i] == max) sb.append(i).append(" "); // 결과값 추가하기

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료