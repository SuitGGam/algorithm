/*
 * 백준 30088번 : 공포의 면담실
 * https://www.acmicpc.net/problem/30088
 * 난이도 : 실버 5
 * 풀이 날짜 : 2025-08-23
 * 간단 설명 : 모든 부서의 퇴근 시간의 합이 가장 최소가 되는 값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class BOJ30088 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 부서의 수
        
        PriorityQueue<Long> pq = new PriorityQueue<>(); // 부서 총 시간을 담을 배열
        long[] sumTime = new long[N + 1]; // 각 부서 총 면담 시간을 저장할 배열
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int human = Integer.parseInt(st.nextToken()); // 부서 직원의 수
            for (int j = 0; j < human; j++) {
                sumTime[i] += Long.parseLong(st.nextToken()); // 각 직원 면담 시간 더하기
            }
            
            pq.add(sumTime[i]); // pq에 넣기
        }
        
        // 버퍼 닫기
        br.close();
        
        long sum = 0; // 총 시간
        long prefixSum = 0; // 누적합
        for (int i = 1; i <= N; i++) {
            prefixSum += pq.poll(); // 누적합 만들기
            sum += prefixSum;
        }
        
        // 결과값 출력하기
        System.out.print(sum);
    } // main 종료
} // Main 종료