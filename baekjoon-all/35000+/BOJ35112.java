/*
 * 백준 35112번 : 으악그래프
 * https://www.acmicpc.net/problem/35112
 * 난이도 : 골드 5
 * 풀이 날짜 : 2026-01-28
 * 간단 설명 : 어떤 임의의 서로 다른 두 간선을 제거해도 그래프가 두 개 이상의 연결 요소로 분리되는지 판별하는 문제
 */

/*
 * 완전 탐색 경우의 수 : MC2 * (M - 2) * 2 (500,000 x 99,999 x 999,998 x 2)
 * 이유 : M개의 간선 중 2개를 고르는 조합을 모두 골라본 후 (간선 - 2) * 2 만큼 탐색을 하면서 연결 요소 확인
 *
 * 알고리즘 : 애드 혹
 * 자료구조 : ArrayList (그래프)
 * 시간 복잡도 : O (1)
 * 풀이 방법 : 으악그래프가 되려면 어떤 경우의 두 간선을 제거해도 항상 성립해야 함
 * 그럼 그래프의 사이클이 없거나 단일 사이클이어야 함
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ35112 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수
        
        for (int i = 0; i < M; i++) br.readLine(); // 입력 처리
        
        // 버퍼 닫기
        br.close();
        
        String ans = "No"; // 으악그래프 유효 여부
        if (M <= N) ans = "Yes";
        
        // 결과값 출력하기
        System.out.print(ans);
    } // main 종료
} // Main 종료