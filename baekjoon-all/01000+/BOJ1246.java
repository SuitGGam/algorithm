/*
 * 백준 1246번 : 온라인 판매
 * https://www.acmicpc.net/problem/1246
 * 난이도 : 실버 4
 * 풀이 날짜 : 2026-03-05
 * 간단 설명 : 최대의 수익을 낼 때의 가격과 달걀 가격을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1246 {
    static final int MAX = 1_000_000; // 최대 가격

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 달걀의 개수
        int M = Integer.parseInt(st.nextToken()); // 잠재적인 고객 수

        int[] price = new int[MAX + 1]; // 가격 배열
        for (int i = 0; i < M; i++) {
            int p_i = Integer.parseInt(br.readLine()); // 고객이 살 수 있는 최대 가격
            price[p_i]++; // 고객이 살 수 있는 가격 카운팅
        }

        // 버퍼 닫기
        br.close();

        for (int i = MAX - 1; i >= 1; i--) price[i] += price[i + 1]; // 가격 카운팅 배열 누적 합 변환

        int minPrice = 0; // 최소 가격
        int maxRevenue = 0; // 최대 수익
        for (int i = MAX; i >= 1; i--) {
            if (price[i] > N) break; // 최대 달갈 수를 넘어가면 종료

            if (maxRevenue < price[i] * i) {
                maxRevenue = price[i] * i; // 최대 수익 갱신
                minPrice = i; // 최소 가격 갱신
            }
        }

        // 결과값 출력하기
        System.out.print(minPrice + " " + maxRevenue);
    } // main 종료
} // Main 종료