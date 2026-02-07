/*
 * 백준 17615번 : 볼 모으기
 * https://www.acmicpc.net/problem/17615
 * 난이도 : 실버 1
 * 풀이 날짜 : 2026-02-08
 * 간단 설명 : 가장 적은 공의 움직임으로 같은 색의 공을 모으는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ17615 {
    static class Ball { // 볼의 색깔과 개수를 나타낼 class
        char color; // 볼의 색깔
        int cnt; // 볼의 개수
        
        Ball(char color, int cnt) {
            this.color = color;
            this.cnt = cnt;
        }
    } // Ball 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 볼의 총 개수
        String color = br.readLine(); // 볼의 색깔을 나타내는 문자열
        
        // 버퍼 닫기
        br.close();
        
        ArrayList<Ball> ball = new ArrayList<>(); // 볼을 압축시킬 ArrayList
        int idx = 0; // 압축시킨 ball의 인덱스
        char prvColor = color.charAt(0); // 이전 볼의 색깔
        int prvCnt = 1; // 이전 색깔 볼의 연속 개수
        ball.add(new Ball(prvColor, prvCnt)); // 첫 번째 볼 add
        
        int redCnt = prvColor == 'R' ? 1 : 0; // 빨간 공의 개수
        int blueCnt = prvColor == 'B' ? 1 : 0; // 파란 공의 개수
        
        for (int i = 1; i < N; i++) {
            char curColor = color.charAt(i); // 현재 볼의 색깔
            
            if (curColor == 'R') redCnt++; // 빨간색 볼 개수 증가
            else blueCnt++; // 파란색 볼 개수 증가
            
            if (curColor == prvColor) { // 이전 볼과 같은 색인 경우
                ball.set(idx, new Ball(prvColor, ++prvCnt)); // 연속 개수 갱신
            } else { // 이전 볼과 다른 색인 경우
                prvColor = curColor; // 현재 볼의 색깔 갱신
                prvCnt = 1; // 현재 볼의 연속 개수 갱신
                idx++; // 인덱스 증가
                ball.add(new Ball(prvColor, prvCnt)); // 새로운 색깔의 볼 add
            }
        }
        
        int minMove = Integer.MAX_VALUE; // 최소 이동횟수 초기화
        if (ball.size() <= 1) minMove = 0; // 같은 색 덩어리가 1개만 있는 경우
        else {
            // 빨간 공을 왼쪽으로
            int rLeft = redCnt;
            if (ball.get(0).color == 'R') rLeft -= ball.get(0).cnt;
            minMove = Math.min(minMove, rLeft);
            
            // 빨간 공을 오른쪽으로
            int rRight = redCnt;
            if (ball.get(ball.size() - 1).color == 'R') rRight -= ball.get(ball.size() - 1).cnt;
            minMove = Math.min(minMove, rRight);
            
            // 파란 공을 왼쪽으로
            int bLeft = blueCnt;
            if (ball.get(0).color == 'B') bLeft -= ball.get(0).cnt;
            minMove = Math.min(minMove, bLeft);
            
            // 파란 공을 오른쪽으로
            int bRight = blueCnt;
            if (ball.get(ball.size() - 1).color == 'B') bRight -= ball.get(ball.size() - 1).cnt;
            minMove = Math.min(minMove, bRight);
        }
        
        // 결과값 출력하기
        System.out.print(minMove);
    } // main 종료
} // Main 종료