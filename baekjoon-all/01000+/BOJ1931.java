/*
 * 백준 1931번 : 회의실 배정
 * https://www.acmicpc.net/problem/1931
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-10-17
 * 간단 설명 : 가장 많은 회의를 할 수 있는 경우의 회의 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ1931 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 회의의 수
        int[][] time = new int[N][2]; // 회의 시간 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()); // 시작 시간
            int end = Integer.parseInt(st.nextToken());   // 종료 시간
            
            time[i][0] = start; // 시작 시간 저장
            time[i][1] = end;   // 종료 시간 저장
        }
        
        // 버퍼 닫기
        br.close();
        
        Arrays.sort(time, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0]; // 끝나는 시간이 같으면 시작 시간 기준 정렬
            return a[1] - b[1]; // 종료 시간 기준 정렬
        });
        
        int maxMeeting = 0; // 회의의 최대 개수
        int beforeEndTime = 0; // 이전 회의 종료 시간
        for (int i = 0; i < N; i++) {
            if (beforeEndTime <= time[i][0]) {
                maxMeeting++; // 이전 회의 종료 시간보다 같거나 늦게 시작한다면 증가
                beforeEndTime = time[i][1]; // 이전 회의 종료 시간 갱신
            }
        }
        
        // 결과값 출력하기
        System.out.print(maxMeeting);
    } // main 종료
} // Main 종료