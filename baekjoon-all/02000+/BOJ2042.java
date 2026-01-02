/*
 * 백준 2042번 : 구간 합 구하기
 * https://www.acmicpc.net/problem/2042
 * 난이도 : 골드 1
 * 풀이 날짜 : 2026-01-02
 * 간단 설명 : 주어진 수열 N에 대하여 특정 수를 변경하고 합을 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N * K (1,000,000 x 10,000)
 * 이유 : 구간의 합을 구할 때 범위가 1부터 N까지면 전체를 계속 구해야 함
 *
 * 알고리즘 : 제곱근 분할
 * 자료구조 : Array
 * 시간 복잡도 : O (√N)
 * 풀이 방법 : 범위 N을 √N 단위로 구간을 분할하여
 * 수의 변경이 일어날 때 해당 구간의 누적 합만 다시 계산
 * 그러면 변경마다 완전 탐색을 안 해도 되고 √N만 작동하면 됨
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2042 {
    static int N, sqrt; // 수의 개수, N의 제곱근
    static long[] arr; // 수 배열
    static long[] rangeSum; // 구간의 누적 합을 저장할 배열
    
    static void update(int idx, long nxt) { // 숫자를 바꾸는 함수
        rangeSum[idx / sqrt] += nxt - arr[idx]; // 구간 합 갱신
        arr[idx] = nxt; // 수 변경
    } // update 종료
    
    static long query(int start, int end) { // 구간의 합을 구하는 함수
        int sGroup = start / sqrt; // 시작 구간 그룹
        int eGroup = end / sqrt;   // 종료 구간 그룹
        
        long sum = 0; // 구간의 합
        if (sGroup == eGroup) { // 시작 구간과 종료 구간이 같은 경우
            for (int i = start; i <= end; i++) sum += arr[i]; // 해당 구간만 더하기
        } else {
            for (int i = start; i < (sGroup + 1) * sqrt; i++) sum += arr[i]; // 시작 구간 그룹 더하기
            for (int i = sGroup + 1; i < eGroup; i++) sum += rangeSum[i]; // 중간 구간 그룹 더하기
            for (int i = eGroup * sqrt; i <= end; i++) sum += arr[i]; // 종료 구간 그룹 더하기
        }
        
        return sum;
    } // query 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 수의 변경 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수
        
        sqrt = (int) Math.sqrt(N); // N의 제곱 근
        arr = new long[N]; // 수 배열
        rangeSum = new long[N / sqrt + 1]; // 구간의 합을 저장할 배열
        
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine()); // 수 저장
            rangeSum[i / sqrt] += arr[i]; // 구간 누적 합 만들기
        }
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String a = st.nextToken(); // 연산 종류
            int b = Integer.parseInt(st.nextToken()); // 변경될 숫자의 순서 or 구간의 합 시작 위치
            long c = Long.parseLong(st.nextToken()); // 변경할 수 or 구간의 합 끝 위치
            
            if (a.equals("1")) update(b - 1, c); // 숫자 바꾸기
            else sb.append(query(b - 1, (int) c - 1)).append("\n"); // 구간의 합 구하기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료