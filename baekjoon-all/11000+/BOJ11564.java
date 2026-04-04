/*
 * 백준 11564번 : 점프왕 최준민
 * https://www.acmicpc.net/problem/11564
 * 난이도 : 실버 4
 * 풀이 날짜 : 2026-04-05
 * 간단 설명 : 준민이가 먹을 수 있는 초콜릿의 최대 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11564 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long k = Long.parseLong(st.nextToken()); // 점프력
        long a = Long.parseLong(st.nextToken()); // 시작 좌표
        long b = Long.parseLong(st.nextToken()); // 끝 좌표

        // 버퍼 닫기
        br.close();

        long cnt = 0; // 초콜릿 개수
        if (a > 0) { // 둘 다 양수인 경우
            cnt = (b / k) - ((a - 1) / k);
        } else if (b < 0) { // 둘 다 음수인 경우
            cnt = (Math.abs(a) / k) - ((Math.abs(b) - 1) / k);
        } else {// 0을 포함하는 경우
            cnt = Math.abs(a) / k + b / k + 1;
        }

        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료