/*
 * 백준 24956번 : 나는 정말 휘파람을 못 불어
 * https://www.acmicpc.net/problem/24956
 * 난이도 : 골드 4
 * 풀이 날짜 : 2026-01-04
 * 간단 설명 :
 */

/*
 * 완전 탐색 경우의 수 : 2^N (2 ^ 200,000)
 * 이유 : 1번 글자 선택/미선택, 2번 글자 선택/미선택, 3번 글자 선택/미선택이라 2^N이 나옴
 *
 * 알고리즘 : 누적 합, 조합론
 * 자료구조 : Array
 * 시간 복잡도 : O (N)
 * 풀이 방법 : W 하나, H 하나 , E 2개 이상이 필요함
 * W와 E를 중간에서 잇는 게 H임
 * W를 앞에서부터 누적 합, E를 뒤에서부터 누적 합을 만듦
 * 그래서 H에 올 때마다 유효한 문자열인 경우 W와 E를 가지고 조합을 통해 개수를 셈
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ24956 {
    static final int MOD = 1_000_000_007;
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 문자열의 길이
        String S = br.readLine(); // 문자열
        
        // 버퍼 닫기
        br.close();
        
        boolean[] existH = new boolean[N]; // H 존재 유무 배열
        int[][] prefixSum = new int[N][2]; // W, E 개수 누적 합 배열
        for (int i = 0; i < N; i++) { // 각 위치 문자 개수 세기
            char cur = S.charAt(i); // 현재 문자
            if (cur == 'W') prefixSum[i][0]++; // W 개수 증가
            else if (cur == 'H') existH[i] = true; // H 존재 체크
            else if (cur == 'E') prefixSum[i][1]++; // E 개수 증가
        }
        
        for (int i = 1; i < N - 3; i++) prefixSum[i][0] += prefixSum[i - 1][0]; // W를 앞에서부터 누적 합 만들기
        for (int i = N - 2; i >= 2; i--) prefixSum[i][1] += prefixSum[i + 1][1]; // E를 뒤에서부터 누적 합 만들기
        
        long[] pow2 = new long[N]; // 2의 거듭제곱 배열
        pow2[0] = 1; // 첫 번째 원소 초기화
        for (int i = 1; i < N; i++) pow2[i] = pow2[i - 1] * 2 % MOD; // 2의 거듭제곱 만들기
        
        long cnt = 0; // 부분 수열의 개수
        for (int i = 1; i < N - 2; i++) {
            if (existH[i]) {
                int wCnt = prefixSum[i - 1][0]; // W의 개수
                int eCnt = prefixSum[i + 1][1]; // E의 개수
                
                if (wCnt == 0 || eCnt < 2) continue; // W나 E의 개수가 모자라면 continue
                
                long comb = pow2[eCnt] - eCnt - 1; // E를 2개 이상 고를 수 있는 경우의 수
                if (comb < 0) comb += MOD;
                
                cnt += wCnt * comb % MOD; // E를 2개 이상 고를 수 있는 경우의 수 * W의 개수
                cnt %= MOD;
            }
        }
        
        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료