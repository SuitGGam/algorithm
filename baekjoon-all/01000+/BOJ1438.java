/*
 * 백준 1438번 : 가장 작은 직사각형
 * https://www.acmicpc.net/problem/1438
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-12-24
 * 간단 설명 : N / 2개 이상의 점을 포함하는 가장 작은 직사각형의 넓이를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ1438 {
    static int N; // 점의 개수
    static int[][] map; // 좌표 배열
    
    static int calArea(int x1, int x2) { // 직사각형의 넓이를 구하는 함수
        if (x1 >= x2) return Integer.MAX_VALUE; // 유효하지 않은 x축 범위면 종료
        
        ArrayList<Integer> y = new ArrayList<>(); // 유효한 y좌표 배열
        for (int i = 0; i < N; i++) if (map[i][0] > x1 && map[i][0] < x2) y.add(map[i][1]); // 유효하면 추가
        
        // 해당 문제에서는 N이 짝수로만 주어지지만
        // 짝수가 아닌 경우는 y.size() * 2 < N이 적절함
        if (y.size() < N / 2) return Integer.MAX_VALUE; // 점의 개수가 유효하지 않으면 종료
        
        Collections.sort(y); // y좌표 오름차순 정렬
        
        int cnt = N / 2; // 포함해야 하는 점의 개수
        int area = Integer.MAX_VALUE; // 직사각형의 넓이
        for (int i = cnt - 1; i < y.size(); i++) { // 가장 작은 직사각형 넓이 구하기
            area = Math.min(area, (y.get(i) + 1 - (y.get(i - cnt + 1) - 1)) * (x2 - x1));
        }
        
        return area; // 직사각형 넓이 반환
    } // calArea 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine()); // 점의 개수
        map = new int[N][2]; // 좌표 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            map[i][0] = Integer.parseInt(st.nextToken()); // X 좌표
            map[i][1] = Integer.parseInt(st.nextToken()); // Y 좌표
        }
        
        // 버퍼 닫기
        br.close();
        
        int minArea = Integer.MAX_VALUE; // 가장 작은 사각형의 넓이
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                minArea = Math.min(minArea, calArea(map[i][0] - 1, map[j][0] + 1)); // 가장 작은 직사각형 넓이 구하기
            }
        }
        
        // 결과값 출력하기
        System.out.print(minArea);
    } // main 종료
} // Main 종료