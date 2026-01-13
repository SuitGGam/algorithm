/*
 * 백준 2531번 : 회전 초밥
 * https://www.acmicpc.net/problem/2531
 * 난이도 : 실버 1
 * 풀이 날짜 : 2026-01-14
 * 간단 설명 : 연속해서 먹는 접시의 수만큼 초밥을 고를 때 가장 많은 가짓수인 경우를 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N * k (30,000 x 3,000)
 * 이유 : 첫 번째 접시부터 마지막 접시까지 순서대로 k개를 고르면서 가짓수를 세야 함
 *
 * 알고리즘 : 슬라이딩 윈도우
 * 자료구조 : Array
 * 시간 복잡도 : O (N)
 * 풀이 방법 : 1번 접시부터 N번 접시까지 k개씩 종류를 세야 하는데
 * 접시마다 k번씩 세면 셌던 걸 계속 또 세야 함
 * 그래서 카운팅 배열로 현재 무슨 접시를 골랐는지 확인하고
 * 현재 k개를 고르기 직전에 있던 접시 제외, 새로 추가된 접시 하나 추가
 * 이런 식으로 중복을 줄여서 코드를 작성하면 됨
 *
 * 배열의 범위를 벗어나는 경우를 잘 처리해야 함
 * ex) N번 접시부터 k개를 고르는 경우
 * k = 4일 때 N, 1, 2, 3
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2531 {
    static int N, d, k; // 접시의 수, 초밥의 종류 수, 연속해서 먹는 접시의 수
    static int[] sushi; // 초밥 배열
    
    static int selectSushi(int c) {
        int[] selectSushi = new int[d + 1]; // 연속해서 선택한 초밥 확인 배열
        selectSushi[c]++; // 쿠폰 사용하기
        
        int curKind = 1; // 현재 초밥 가짓수
        for (int i = 1; i <= k; i++) { // 초기 k개 초밥 선택
            int cur = sushi[i]; // 현재 초밥
            selectSushi[cur]++; // 초밥 선택
            if (selectSushi[cur] == 1) curKind++; // 종류 수 증가
        }
        
        int maxKind = curKind; // 초밥 최대 가짓수
        
        for (int i = 2; i <= N; i++) {
            // 이전에 골랐던 초밥 제외
            int prv = sushi[i - 1]; // 제외된 초밥
            if (selectSushi[prv] == 1) curKind--; // 종류 수 감소
            selectSushi[prv]--; // 선택 제외
            
            int cur = sushi[i + k - 1]; // 추가된 초밥
            if (selectSushi[cur] == 0) curKind++; // 종류 수 증가
            selectSushi[cur]++; // 초밥 선택
            
            maxKind = Math.max(maxKind, curKind); // 최대 종류 수 갱신
        }
        
        return maxKind; // 초밥 최대 가짓수 반환
    } // selectSushi 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 접시의 수
        d = Integer.parseInt(st.nextToken()); // 초밥의 종류 수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
        
        sushi = new int[N * 2 + 1]; // 초밥 배열
        for (int i = 1, j = N + 1; i <= N; i++, j++) {
            int num = Integer.parseInt(br.readLine()); // 초밥 번호
            sushi[i] = num; // 초밥 저장
            sushi[j] = num; // 초밥 저장
        }
        
        // 버퍼 닫기
        br.close();
        
        int maxKind = selectSushi(c); // 초밥 최대 가짓수 찾기
        
        // 결과값 출력하기
        System.out.print(maxKind);
    } // main 종료
} // Main 종료