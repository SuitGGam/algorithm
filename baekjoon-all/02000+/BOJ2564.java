/*
 * 백준 2564번 : 경비원
 * https://www.acmicpc.net/problem/2564
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-10-11
 * 간단 설명 : 회사에서 각 상점까지의 최단 거리의 합을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2564 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int width, height, perimeter; // 가로 길이, 세로 길이, 네모의 둘레 길이
    static int straightenOut(int dir, int loc) { // 네모를 일자로 펴서 거리를 구하는 함수
        // 북동남서 순서로 펴기
        if (dir == 1) return loc; // 북쪽
        else if (dir == 2) return width + height + (width - loc); // 남쪽
        else if (dir == 3) return 2 * width + height + (height - loc); // 서쪽
        else return width + loc; // 동쪽
    } // straightenOut 종료
    
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine(), " ");
        width  = Integer.parseInt(st.nextToken()); // 가로 길이
        height = Integer.parseInt(st.nextToken()); // 세로 길이
        
        perimeter = 2 * (width + height); // 네모의 둘레 길이
        
        int storeCnt = Integer.parseInt(br.readLine()); // 상점의 개수
        int[] storeDistance = new int[storeCnt]; // 상점들의 거리 배열
        for (int i = 0; i < storeCnt; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int dir = Integer.parseInt(st.nextToken()); // 상점의 방향
            int loc = Integer.parseInt(st.nextToken()); // 경계선으로부터의 상점의 거리
            
            storeDistance[i] = straightenOut(dir, loc); // 상점들의 거리
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        int dongDir = Integer.parseInt(st.nextToken()); // 동근이의 방향
        int dongLoc = Integer.parseInt(st.nextToken()); // 경계선으로부터의 동근이의 거리
        
        int dongDistance = straightenOut(dongDir, dongLoc); // 동근이의 거리
        
        // 버퍼 닫기
        br.close();
        
        int sumMinDistance = 0; // 최단 거리의 합
        for (int i = 0; i < storeCnt; i++) {
            int diff = Math.abs(dongDistance - storeDistance[i]); // 동근이와 상점의 거리 차이
            sumMinDistance += Math.min(diff, perimeter - diff); // 시계 방향, 반 시계 방향 중에 짧은 길이
        }
        
        // 결과값 출력하기
        System.out.print(sumMinDistance);
    } // main 종료
} // Main 종료