/*
 * 백준 27966번 : △
 * https://www.acmicpc.net/problem/27966
 * 난이도 : 실버 3
 * 풀이 날짜 : 2026-03-17
 * 간단 설명 : N개의 정점으로 이루어진 트리의 모든 정점 쌍에 대해 거리 합의 최소를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ27966 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 정점의 개수

        // 버퍼 닫기
        br.close();

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        sb.append((long) (N - 1) * (N - 1)).append("\n"); // 거리의 합 추가하기
        for (int i = 2; i <= N; i++) {
            sb.append("1 ").append(i).append("\n"); // 결과값 추가하기
        }

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료