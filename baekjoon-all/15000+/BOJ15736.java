/*
 * 백준 15736번 : 청기 백기
 * https://www.acmicpc.net/problem/15736
 * 난이도 : 실버 4
 * 풀이 날짜 : 2026-01-15
 * 간단 설명 : 1 ~ N까지 각 배수에 해당하는 깃발을 뒤집었을 때 최종적으로 백기가 위인 게 몇 개인지 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N * (N + 1) / 2 (2,100,000,000 x (2,100,000,000 + 1)) / 2
 * 이유 : 각 배수에 맞는 깃발을 모두 뒤집어 봐야 함
 *
 * 알고리즘 : 수학
 * 자료구조 : X
 * 시간 복잡도 : O (√N)
 * 풀이 방법 : N이 25정도까지만 직접 해보니
 * 흰색 깃발이 1개씩 늘어나는 텀이 규칙적으로 있음
 * 깃발의 개수가 3개까지는 1개, 8개까지는 2개, 15개까지는 3개, •••
 * 이런 식으로 3, 5, 7, 9. 2개의 텀마다 깃발이 1개씩 증가함
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15736 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 깃발의 개수
        
        // 버퍼 닫기
        br.close();
        
        int ans = 0; // 백색 깃발의 개수
        long num = 0; // 숫자
        for (int i = 3, j = 1; num < N; i += 2, j++) {
            num += i;
            ans = j;
        }
        
        // 결과값 출력하기
        System.out.print(ans);
    } // main 종료
} // Main 종료