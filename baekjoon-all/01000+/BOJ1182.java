/*
 * 백준 1182번 : 부분수열의 합
 * https://www.acmicpc.net/problem/1182
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-12-23
 * 간단 설명 : 부분 수열의 합이 S인 경우의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ1182 {
    static int N, S; // 정수의 개수, 부분 수열의 합
    static int[] num; // 정수 수열
    static int sCnt; // 합이 S가 되는 부분 수열의 개수
    static void partialSum (int cur, int prvSum) { // 부분 수열의 합을 구하는 함수
        // 종료 조건
        if (cur == N) {
            if (prvSum == S) sCnt++; // 부분 수열의 합이 S와 같으면 카운트
            return; // 수열을 다 탐색했으면 종료
        }
        
        partialSum(cur + 1, prvSum); // 현재 숫자를 안 고른 경우
        
        // 가지치기
        int curSum = prvSum + num[cur]; // 현재 부분 수열의 합
        if (curSum > S && num[cur] >= 0) return; // 현재 부분 수열의 합이 S보다 큰 경우 종료
        partialSum(cur + 1, curSum); // 현재 숫자를 고른 경우
    } // partialSum 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 정수의 개수
        S = Integer.parseInt(st.nextToken()); // 원소의 합
        
        num = new int[N]; // 정수 수열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken()); // 수열 저장
        
        // 버퍼 닫기
        br.close();
        
        sCnt = 0; // 합이 S가 되는 부분 수열의 개수 초기화
        Arrays.sort(num); // 수열 비내림차순 정렬
        partialSum(0, 0); // 첫 번째 숫자를 고른 경우
        
        if (S == 0) sCnt--; // S가 0인 경우 모두 안 고른 경우 제외
        
        // 결과값 출력하기
        System.out.print(sCnt);
    } // main 종료
} // Main 종료