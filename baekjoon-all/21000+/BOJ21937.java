/*
 * 백준 21937번 : 작업
 * https://www.acmicpc.net/problem/21937
 * 난이도 : 실버 1
 * 풀이 날짜 : 2026-02-28
 * 간단 설명 : X 작업을 처리하기 위해 선행해야 할 작업의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class BOJ21937 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 작업의 개수
        int M = Integer.parseInt(st.nextToken()); // 정보의 개수
        ArrayList<Integer>[] work = new ArrayList[N + 1]; // 작업 배열
        for (int i = 1; i <= N; i++) work[i] = new ArrayList<>(); // 작업 생성
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int first  = Integer.parseInt(st.nextToken()); // 선행 작업
            int follow = Integer.parseInt(st.nextToken()); // 후행 작업

            work[follow].add(first); // 후행 작업에 선행 작업을 add
        }

        int X = Integer.parseInt(br.readLine()); // 끝내야 하는 작업
        int cnt = 0; // 선행 작업의 수

        ArrayDeque<Integer> queue = new ArrayDeque<>(); // 선행 작업을 넣을 Queue
        boolean[] visited = new boolean[N + 1]; // 방문 처리 배열
        queue.add(X); // 마지막 작업 add
        visited[X] = true; // 마지막 작업 방문 처리
        while (!queue.isEmpty()) {
            int cur = queue.poll(); // 현재 작업

            for (int nxt : work[cur]) {
                if (visited[nxt]) continue; // 이미 한 작업이면 continue

                queue.add(nxt); // 다음 선행 작업 add
                visited[nxt] = true; // 방문 처리
                cnt++; // 작업 추가
            }
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료