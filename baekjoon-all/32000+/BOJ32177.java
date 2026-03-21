/*
 * 백준 32177번 : 에어드롭
 * https://www.acmicpc.net/problem/32177
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-03-21
 * 간단 설명 : 푸앙이와 함께 찍은 사진을 푸앙이에게 전달할 수 있는 친구를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Collections;

public class BOJ32177 {
    static int N; // 친구의 수
    static int[][] friend; // 친구 배열
    static ArrayList<Integer>[] airDrop; // 에어드롭이 가능한 친구 관계 배열

    static ArrayList<Integer> bfs() { // 푸앙이가 사진을 받을 수 있게 전달하는 함수
        ArrayList<Integer> result = new ArrayList<>(); // 결과값을 저장할 객체

        boolean[] visited = new boolean[N + 1]; // 방문 처리 배열
        ArrayDeque<Integer> queue = new ArrayDeque<>(); // 방문 순서를 담을 Queue

        queue.add(0); // 시작점 add
        visited[0] = true; // 시작점 방문 처리

        while (!queue.isEmpty()) {
            int cur = queue.poll(); // 현재 친구

            if (friend[cur][3] == 1) result.add(cur); // 사진을 가지고 있는 친구를 발견 시 add

            for (int nxt : airDrop[cur]) {
                if (visited[nxt]) continue; // 이미 방문한 곳이면 continue

                queue.add(nxt); // 다음 친구 add
                visited[nxt] = true; // 방문 처리
            }
        }

        return result; // 결과값 반환
    } // bfs 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 친구의 수
        int K = Integer.parseInt(st.nextToken()); // 최대 거리
        int T = Integer.parseInt(st.nextToken()); // 최대 버전 차이

        friend = new int[N + 1][4]; // 친구 배열
        st = new StringTokenizer(br.readLine(), " ");
        friend[0][0] = Integer.parseInt(st.nextToken()); // 푸앙이의 X 좌표
        friend[0][1] = Integer.parseInt(st.nextToken()); // 푸앙이의 Y 좌표
        friend[0][2] = Integer.parseInt(st.nextToken()); // 푸앙이의 휴대폰 버전

        airDrop = new ArrayList[N + 1]; // 에어드롭이 가능한 친구 관계 배열
        for (int i = 0; i <= N; i++) airDrop[i] = new ArrayList<>(); // 친구 생성

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            friend[i][0] = Integer.parseInt(st.nextToken()); // X 좌표
            friend[i][1] = Integer.parseInt(st.nextToken()); // Y 좌표
            friend[i][2] = Integer.parseInt(st.nextToken()); // 휴대폰 버전
            friend[i][3] = Integer.parseInt(st.nextToken()); // 사진 유무

            for (int j = 0; j < i; j++) {
                int x = Math.abs(friend[i][0] - friend[j][0]); // X 차이
                int y = Math.abs(friend[i][1] - friend[j][1]); // Y 차이
                if (Math.abs(friend[i][2] - friend[j][2]) <= T && K * K >= x * x + y * y) {
                    // 관계 연결
                    airDrop[i].add(j);
                    airDrop[j].add(i);
                }
            }
        }

        // 버퍼 닫기
        br.close();

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        ArrayList<Integer> result = bfs(); // 에어드롭하기
        if (result.isEmpty()) sb.append(0);
        else {
            Collections.sort(result); // 친구 순서 정렬
            for (int i = 0; i < result.size(); i++) sb.append(result.get(i)).append(" "); // 결과값 추가하기
        }

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료