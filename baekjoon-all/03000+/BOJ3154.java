/*
 * 백준 3154번 : 알람시계
 * https://www.acmicpc.net/problem/3154
 * 난이도 : 브론즈 1
 * 풀이 날짜 : 2025-09-23
 * 간단 설명 : 최소 노력이 드는 알람 시간 중 가장 빠른 시간을 찾는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3154 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int[][] numbers = { {4, 2}, {1, 1}, {1, 2}, {1, 3}, {2, 1}, {2, 2},
            {2, 3}, {3, 1}, {3, 2}, {3, 3} }; // 키보드 위치 배열
    
    static int hour, min; // 시간, 분
    static int minEffort; // 최소 노력
    static String fastTime; // 노력이 가장 적게 드는 가장 빠른 시각
    static int[] time; // 시간 조합 배열
    static void setAlarm() { // 알람을 맞추는 함수
        for (int h = hour; h < 100; h += 24) { // 시간
            for (int m = min; m < 100; m += 60) { // 분
                time = new int[4]; // 시간 세팅 배열 크기 지정
                time[0] = h / 10; // 시
                time[1] = h % 10; // 간
                time[2] = m / 10;  // 세
                time[3] = m % 10;  // 팅
                
                getAnswer(); // 노력 구하기
            }
        }
    } // setAlarm 종료
    
    static void getAnswer() { // 노력을 구하는 함수
        int sum = 0; // 노력의 합
        for (int i = 1; i <= 3; i++) {
            sum += Math.abs(numbers[time[i - 1]][0] - numbers[time[i]][0]); // x좌표 노력의 값
            sum += Math.abs(numbers[time[i - 1]][1] - numbers[time[i]][1]); // y좌표 노력의 값
        }
        
        if (minEffort > sum) { // 노력이 더 작은 경우
            minEffort = sum;  // 최소 노력 갱신
            fastTime = "" + time[0] + time[1] + ":" + time[2] + time[3]; // 시간 갱신
        } else if (minEffort == sum) { // 노력이 같은 경우
            if ((int) fastTime.charAt(0) - '0' >= time[0]) {
                if ((int) fastTime.charAt(1) - '0' >= time[1]) {
                    if ((int) fastTime.charAt(3) - '0' >= time[2]) {
                        if ((int) fastTime.charAt(4) - '0' >= time[3]) {
                            fastTime = "" + time[0] + time[1] + ":" + time[2] + time[3]; // 시간 갱신
                        }
                    }
                }
            }
        }
    } // getAnswer 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), ":");
        hour = Integer.parseInt(st.nextToken()) % 24; // 시간
        min  = Integer.parseInt(st.nextToken()) % 60; // 분
        
        // 버퍼 닫기
        br.close();
        
        minEffort = Integer.MAX_VALUE; // 최소 노력 초기화
        setAlarm(); // 알람 맞추기
        
        // 결과값 출력하기
        System.out.print(fastTime);
    } // main 종료
} // Main 종료