/*
 * 백준 16114번 : 화살표 연산자
 * https://www.acmicpc.net/problem/16114
 * 난이도 : 실버 3
 * 풀이 날짜 : 2026-03-07
 * 간단 설명 : 출력되는 수의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16114 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int X = Integer.parseInt(st.nextToken()); // 초깃값
        int N = Integer.parseInt(st.nextToken()); // 화살표의 길이

        // 버퍼 닫기
        br.close();

        boolean finish = true; // 동작해서 끝나는 유효성
        String result = "ERROR"; // 결과
        int cnt = -1; // 출력된 수의 개수

        if (N / 2 >= 1 && N % 2 == 1) { // 컴파일이 안 되는 경우
            finish = false;
            result = "ERROR";
        } else if ((X > 0 && N == 0) || (X < 0 && N == 1)) { // 출력이 무한대인 경우
            finish = false;
            result = "INFINITE";
        } else {
            if (X == 0 || X < 0 || (X > 0 && N == 1)) cnt = 0; // 0개 출력되는 경우
            else { // 출력되는 수가 있는 경우
                int decrease = N / 2;
                while (X > 0) {
                    X -= decrease; // X 감소
                    cnt++; // 출력되는 수 증가
                }
            }
        }

        // 결과값 출력하기
        System.out.print(finish ? cnt : result);
    } // main 종료
} // Main 종료