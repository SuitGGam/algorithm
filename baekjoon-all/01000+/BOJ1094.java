/*
 * 백준 1094번 : 막대기
 * https://www.acmicpc.net/problem/1094
 * 난이도 : 실버 5
 * 풀이 날짜 : 2026-02-27
 * 간단 설명 : X cm의 막대기를 만들 때 필요한 막대의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1094 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine()); // 만들어야 할 막대의 길이

        // 버퍼 닫기
        br.close();

        int num = 64; // 막대의 시작 길이
        int cnt = 0; // 막대의 개수
        while (X > 0) {
            if (X < num) { // X보다 막대의 길이가 더 긴 경우
                num /= 2;
            } else if (X == num) { // X랑 막대의 길이가 똑같은 경우
                X -= num;
                cnt++;
            } else { // X보다 막대의 길이가 짧은 경우
                X -= num;
                num /= 2;
                cnt++;
            }
        }

        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료