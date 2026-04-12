/*
 * 백준 2785번 : 체인
 * https://www.acmicpc.net/problem/2785
 * 난이도 : 실버 1
 * 풀이 날짜 : 2026-04-12
 * 간단 설명 : 체인을 하나로 연결하기 위해 최소로 쓸 수 있는 고리의 수를 구하는 문제
 */

/*
고리 1개를 풀면 체인 2개를 연결할 수 있음
4 3 5 7 9
4를 하나 풀어서 7, 9 연결
4를 하나 풀어서 5, 7 연결
4를 하나 풀어서 3, 5 연결
4를 하나 풀어서 4, 3 연결
이렇게 하면 하나로 만들 수 있음

하지만 함정이 하나 있음
길이가 큰 것부터 풀면 고리를 푸는 과정에서
체인이 늦게 해체돼서 고리를 더 사용해야 하는 경우 발생
그래서 '길이가 짧은 체인'부터 하나씩 해체하면서 연결해야 함
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ2785 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 체인의 수
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] chain = new int[N]; // 체인 길이 배열
        for (int i = 0; i < N; i++) chain[i] = Integer.parseInt(st.nextToken()); // 체인 길이

        // 버퍼 닫기
        br.close();

        Arrays.sort(chain); // 체인 길이 비내림차순 정렬
        int idx = 0; // 고리를 풀 체인의 인덱스
        int used = 0; // 사용한 고리의 수
        int remain = N; // 남은 체인의 수
        while (remain > 1) {
            if (chain[idx] > 0) {
                chain[idx]--; // 고리 하나 사용
                used++; // 사용한 고리 수 증가
                remain--; // 남은 체인의 수 감소

                if (chain[idx] == 0) {
                    idx++; // 인덱스 증가
                    remain--; // 남은 체인의 수 감소
                }
            }
        }

        // 결과값 출력하기
        System.out.print(used);
    } // main 종료
} // Main 종료