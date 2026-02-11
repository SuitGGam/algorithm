/*
 * 백준 28353번 : 고양이 카페
 * https://www.acmicpc.net/problem/28353
 * 난이도 : 실버 3
 * 풀이 날짜 : 2026-02-12
 * 간단 설명 : 한 명이 버틸 수 있는 최대 무게가 K일 때 행복해질 수 있는 최대 사람의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ28353 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 고양이의 수
        int K = Integer.parseInt(st.nextToken()); // 한 명이 버틸 수 있는 최대 무게

        int[] weight = new int[N]; // 고양이 무게 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) weight[i] = Integer.parseInt(st.nextToken()); // 무게 저장

        // 버퍼 닫기
        br.close();

        Arrays.sort(weight); // 고양이 무게 정렬

        int maxPeople = 0; // 행복해질 수 있는 최대 사람의 수
        int left = 0; // 왼쪽 포인터
        int right = N - 1; // 오른쪽 포인터
        while (left < right) {
            if (weight[left] + weight[right] <= K) { // 한 사람이 들 수 있는 경우
                maxPeople++; // 행복해질 수 있는 사람 수 증가
                left++; // 왼쪽 포인터 이동
                right--; // 오른쪽 포인터 이동
            } else right--; // 오른쪽 포인터 이동
        }

        // 결과값 출력하기
        System.out.print(maxPeople);
    } // main 종료
} // Main 종료