/*
 * 백준 18110번 : solved.ac
 * https://www.acmicpc.net/problem/18110
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-08-02
 * 간단 설명 : 난이도의 절사평균을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ18110 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 회원의 수

        int[] opinion = new int [N]; // 의견을 저장할 배열
        for (int i = 0; i < N; i++) {
            opinion[i] = Integer.parseInt(br.readLine()); // 난이도 저장
        }

        // 버퍼 닫기
        br.close();

        // 난이도 비내림차순 정렬
        Arrays.sort(opinion);

        int truncation = (int) (Math.round((N * 0.15) * 1 / 1.0)); // 앞뒤로 절사할 15% 구하기

        int sum = 0; // 30%를 절사한 난이도의 총합
        for (int i = truncation; i < N - truncation; i++) {
            sum += opinion[i]; // 난이도 더하기
        }

        int difficulty = (int) (Math.round(((double) sum / (N - 2 * truncation)) * 1 / 1.0)); // 절사평균 난이도

        // 결과값 출력하기
        System.out.print(difficulty);
    } // main 종료
} // Main 종료