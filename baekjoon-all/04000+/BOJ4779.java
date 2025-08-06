/*
 * 백준 4779번 : 칸토어 집합
 * https://www.acmicpc.net/problem/4779
 * 난이도 : 실버 3
 * 풀이 날짜 : 2025-08-07
 * 간단 설명 : 재귀로 칸토어 집합을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ4779 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static char[] cantorSet; // -를 담을 배열
    static void recursion(int start, int len) { // 칸토어 집합 구하는 함수
        // 종료 조건
        if (len == 1) return;

        len /= 3; // 가운데 범위 찾기
        for (int i = start + len; i < start + 2 * len; i++) {
            cantorSet[i] = ' '; // 공백으로 바꾸기
        }

        recursion(start, len); // 왼쪽 부분 재귀
        recursion(start + 2 * len, len); // 오른쪽 부분 재귀
    } // cantor 종료

    public static void main(String[] args) throws IOException {
        String N; // 숫자 N
        while ((N = br.readLine()) != null) {
            int num = Integer.parseInt(N); // 숫자로 변환
            int len = (int) Math.pow(3, num);

            cantorSet = new char[len]; // 칸토어 배열 크기 지정
            Arrays.fill(cantorSet, '-'); // 배열 -로 채우기

            recursion(0, len); // 재귀 시작
            sb.append(cantorSet, 0, cantorSet.length).append("\n"); // 결과값 추가하기
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료