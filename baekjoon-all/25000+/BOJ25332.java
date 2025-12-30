/*
 * 백준 25332번 : 수들의 합 8
 * https://www.acmicpc.net/problem/25332
 * 난이도 : 골드 3
 * 풀이 날짜 : 2025-12-31
 * 간단 설명 : 길이가 같은 두 수열 A, B에 대해서 구간의 합이 같은 순서쌍의 개수를 구하는 문제
 */

/*
 * 최대 경우의 수 : n(n + 1) / 2
 * 두 지점 (i, j)에 대해서 골라서 전부 확인해야 함
 * 그럼 수열의 길이 N에 대하여
 * 길이가 1인 구간 N개, 길이가 2인 구간 N - 1개, ···, 길이가 N인 구간 1개
 * 시간 복잡도 : O (N)
 * 이유 : A와 B의 차이 누적 합이 나온 횟수만 세면서 갱신하면 됨
 * 알고리즘 : 누적 합
 * 자료구조 : Array, HashMap
 * 풀이 방법 : A1 + A2 + A3 = B1 + B2 + B3 라고 할 때
 * (A1 + A2 + A3) - (B1 + B2 + B3) = 0 임
 * 다르게 해석하면
 * (A1 - B1) + (A2 - B2) + (A3 - B3) = 0 으로도 볼 수 있음
 * 위의 성질을 이용해서 A와 B의 차이만큼의 누적 합의 빈도를 세면서 순서쌍을 세면 됨
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class BOJ25332 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 수열의 길이
        
        int[] A = new int[N]; // A 수열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken()); // A 수열 정보 저장
        
        int[] B = new int[N]; // B 수열
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) B[i] = Integer.parseInt(st.nextToken()); // B 수열 정보 저장
        
        // 버퍼 닫기
        br.close();
        
        long pairCnt = 0; // 순서쌍 개수
        long curPrefixSum = 0L; // 현재까지의 A와 B의 차이 누적 합
        
        HashMap<Long, Long> cntMap = new HashMap<>(); // 누적 합의 빈도수를 저장할 Map
        cntMap.put(0L, 1L); // 누적 합이 0이 됐을 때를 대비한 초깃값 설정
        for (int i = 0; i < N; i++) {
            curPrefixSum += (A[i] - B[i]); // A와 B의 차이만큼 더하기
            
            // 현재의 누적 합이 이미 나왔었다면 나온 횟수만큼 순서쌍 증가
            // 이유 : 현재의 누적 합과 과거의 누적 합 사이의 합은 0임
            if (cntMap.containsKey(curPrefixSum)) pairCnt += cntMap.get(curPrefixSum);
            
            // 누적 합 빈도 갱신
            cntMap.put(curPrefixSum, cntMap.getOrDefault(curPrefixSum, 0L) + 1);
        }
        
        // 결과값 출력하기
        System.out.print(pairCnt);
    } // main 종료
} // Main 종료