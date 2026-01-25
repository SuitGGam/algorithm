/*
 * 백준 30891번 : 볶음밥 지키기
 * https://www.acmicpc.net/problem/30891
 * 난이도 : 실버 5
 * 풀이 날짜 : 2026-01-25
 * 간단 설명 : 어느 위치에 웍을 뒀을 때 가장 많은 밥알을 지킬 수 있는지 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N * x * y (100 x 200 x 200)
 * 이유 : 모든 (x, y) 좌표에 대해 밥알의 개수만큼 웍의 범위에 드는지 확인하면 됨
 *
 * 알고리즘 : 브루트 포스
 * 자료구조 : Array
 * 시간 복잡도 : O (N * (x + y))
 * 풀이 방법 : x와 y의 범위가 매우 작음
 * 그러니 모든 좌표에 대해서 완전 탐색으로 진행
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ30891 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken()); // 밥알의 개수
        int R = Integer.parseInt(st.nextToken()); // 웍의 반지름
        
        int[][] rice = new int[N][2]; // 밥알 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            rice[i][0] = Integer.parseInt(st.nextToken()); // 밥알의 x 좌표
            rice[i][1] = Integer.parseInt(st.nextToken()); // 밥알의 y 좌표
        }
        
        // 버퍼 닫기
        br.close();
        
        int wokX = 0; // 웍의 x 좌표
        int wokY = 0; // 웍의 y 좌표
        int cnt  = 0; // 밥알의 수
        for (int x = -100; x <= 100; x++) {
            for (int y = -100; y <= 100; y++) {
                int tmpCnt = 0; // 웍 안에 있는 밥알의 개수
                for (int i = 0; i < N; i++) {
                    int xSqrt = x - rice[i][0]; // x에 대한 값
                    int ySqrt = y - rice[i][1]; // y에 대한 값
                    if (xSqrt * xSqrt + ySqrt * ySqrt <= R * R) tmpCnt++; // 웍의 범위에 포함
                }
                
                if (cnt < tmpCnt) { // 최대 밥알 좌표, 개수 갱신
                    wokX = x; // x 좌표 갱신
                    wokY = y; // y 좌표 갱신
                    cnt  = tmpCnt; // 개수 갱신
                }
            }
        }
        
        // 결과값 출력하기
        System.out.print(wokX + " " + wokY);
    } // main 종료
} // Main 종료