/*
 * 백준 1654번 : 랜선 자르기
 * https://www.acmicpc.net/problem/1654
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-10-15
 * 간단 설명 : 보유한 랜선을 특정 길이로 잘랐을 때 조건을 만족하는 가장 긴 길이를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1654 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int K, N; // 보유한 랜선의 개수, 필요한 랜선의 개수
    static int maxLan; // 보유한 랜선 중 가장 길이가 긴 랜선
    static int[] lan; // 랜선 길이 배열
    static long maxLen; // 랜선 최대 길이
    
    static void cutLan() { // 랜선을 자르는 함수
        long s = 1; // 시작 범위
        long e = maxLan; // 끝 범위
        
        while (s <= e) {
            long mid = (s + e) / 2; // 중간값 갱신
            long cnt = 0; // 만든 랜선의 개수
            for (int i = 0; i < K; i++) {
                cnt += lan[i] / mid;
            }
            
            if (cnt >= N) { // 조건을 만족하는 경우
                maxLen = mid; // 최대 길이 갱신
                s = mid + 1; // 범위 갱신
            } else { // 조건을 만족 못 하는 경우
                e = mid - 1; // 범위 갱신
            }
        }
    } // cutLan 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        K = Integer.parseInt(st.nextToken()); // 보유한 랜선의 개수
        N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수
        
        lan = new int[K]; // 배열 크기 지정
        maxLan = 0; // 길이 초기화
        for (int i = 0; i < K; i++) {
            lan[i] = Integer.parseInt(br.readLine()); // 랜선의 길이
            if (maxLan < lan[i]) maxLan = lan[i]; // 보유한 최대 길이 랜선 갱신
        }
        
        // 버퍼 닫기
        br.close();
        
        maxLen = 0; // 랜선 최대 길이 초기화
        cutLan(); // 랜선 자르기
        
        // 결과값 출력하기
        System.out.print(maxLen);
    } // main 종료
} // Main 종료