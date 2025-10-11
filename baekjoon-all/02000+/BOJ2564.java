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
    
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine(), " ");
        int width  = Integer.parseInt(st.nextToken()); // 가로 길이
        int height = Integer.parseInt(st.nextToken()); // 세로 길이
        
        int storeCnt = Integer.parseInt(br.readLine()); // 상점의 개수
        int[][] location = new int[storeCnt][2]; // 상점 위치 배열
        for (int i = 0; i < storeCnt; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int dir = Integer.parseInt(st.nextToken()); // 상점의 방향
            int loc = Integer.parseInt(st.nextToken()); // 경계선으로부터의 상점의 거리
            
            location[i][0] = dir;
            location[i][1] = loc;
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        int dongDir = Integer.parseInt(st.nextToken()); // 동근이의 방향
        int dongloc = Integer.parseInt(st.nextToken()); // 경계선으로부터의 동근이의 거리
        
        // 버퍼 닫기
        br.close();
        
        int sumMinDistance = 0; // 최단 거리의 합
        for (int i = 0; i < storeCnt; i++) {
            if (dongDir == location[i][0]) { // 같은 방향인 경우
                sumMinDistance += Math.abs(dongloc - location[i][1]); // 거리 차이만큼 더해주기
            } else if ((dongDir == 1 && location[i][0] == 2) || (dongDir == 2 && location[i][0] == 1)) { // 남북, 북남인 경우
                sumMinDistance += Math.min((dongloc + location[i][1]), (2 * width - dongloc - location[i][1])) + height;
            } else if ((dongDir == 3 && location[i][0] == 4) || (dongDir == 4 && location[i][0] == 3)) { // 동서, 서동인 경우
                sumMinDistance += Math.min((dongloc + location[i][1]), (2 * height - dongloc - location[i][1])) + width;
            } else if ((dongDir == 1 || dongDir == 2) && (location[i][0] == 3 || location[i][0] == 4)) { // 동근이가 남 또는 북이고 상점이 동 또는 서인 경우
                if (dongDir == 1 && location[i][0] == 3) sumMinDistance += dongloc + location[i][1];
                else if (dongDir == 1 && location[i][0] == 4) sumMinDistance += width - dongloc + location[i][1];
                else if (dongDir == 2 && location[i][0] == 3) sumMinDistance += dongloc + height - location[i][1];
                else sumMinDistance += width - dongloc + height - location[i][1];
            } else { // 동근이가 동 또는 서이고 상점이 남 또는 북인 경우
                if (dongDir == 3 && location[i][0] == 1) sumMinDistance += dongloc + location[i][1];
                else if (dongDir == 3 && location[i][0] == 2) sumMinDistance += height - dongloc + location[i][1];
                else if (dongDir == 4 && location[i][0] == 1) sumMinDistance += dongloc + width - location[i][1];
                else sumMinDistance += height - dongloc + width - location[i][1];
            }
        }
        
        // 결과값 출력하기
        System.out.print(sumMinDistance);
    } // main 종료
} // Main 종료