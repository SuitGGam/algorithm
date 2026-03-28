/*
 * 백준 30892번 : 상어 키우기
 * https://www.acmicpc.net/problem/30892
 * 난이도 : 실버 1
 * 풀이 날짜 : 2026-03-29
 * 간단 설명 : 상어가 최대로 키울 수 있는 몸집의 크기를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayDeque;

public class BOJ30892 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 상어의 수
        int K = Integer.parseInt(st.nextToken()); // 먹을 수 있는 상어의 수
        long T = Long.parseLong(st.nextToken()); // 최초 크기

        st = new StringTokenizer(br.readLine(), " ");
        long[] size = new long[N]; // 상어 크기 배열
        for (int i = 0; i < N; i++) size[i] = Long.parseLong(st.nextToken()); // 상어 크기 저장

        Arrays.sort(size); // 상어 크기 비내림차순 정렬

        // 버퍼 닫기
        br.close();

        ArrayDeque<Long> stack = new ArrayDeque<>(); // 먹을 수 있는 상어를 담을 Stack
        int idx = 0; // 상어 크기 인덱스

        for (int i = 0; i < K; i++) {
            while (idx < N && size[idx] < T) {
                stack.push(size[idx++]); // 현재 크기보다 작은 상어 push
            }

            if (!stack.isEmpty()) T += stack.pop(); // 크기 증가
            else break;
        }

        // 결과값 출력하기
        System.out.print(T);
    } // main 종료
} // Main 종료