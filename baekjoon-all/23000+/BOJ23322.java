/*
 * 백준 23322번 : 초콜릿 뺏어 먹기
 * https://www.acmicpc.net/problem/23322
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-02-13
 * 간단 설명 : 연두에게 들키지 않고 초콜릿을 먹을 수 있는 최대 개수와 최소 날짜를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ23322 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 가로 이동 칸 수
        int K = Integer.parseInt(st.nextToken()); // 세로 이동 칸 수

        int[] choco = new int[N + 1]; // 초콜릿 개수 배열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) choco[i] = Integer.parseInt(st.nextToken()); // 초콜릿 개수 저장

        // 버퍼 닫기
        br.close();

        int maxEat = 0; // 최대로 먹을 수 있는 초콜릿 개수
        int minDay = 0; // 초콜릿을 먹는데 걸리는 최소 일 수
        while (choco[1] != choco[N]) {
            for (int i = K + 1; i <= N; i++) {
                if (choco[i] == choco[i - K]) continue; // 먹으려는 통과 비교하려는 통의 개수가 같다면 continue

                maxEat += choco[i] - choco[i - K]; // 초콜릿 먹기
                choco[i] = choco[i - K]; // 개수 변경
                break;
            }

            Arrays.sort(choco); // 재정렬
            minDay++; // 하루 증가
        }

        // 결과값 출력하기
        System.out.print(maxEat + " " + minDay);
    } // main 종료
} // Main 종료