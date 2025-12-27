/*
 * 백준 14846번 : 직사각형과 쿼리
 * https://www.acmicpc.net/problem/14846
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-12-28
 * 간단 설명 : 쿼리에 주어지는 사각형 범위에 대한 서로 다른 정수의 개수를 파악하는 문제
 */

/*
 * 최대 경우의 수 : 90000 x 100,000 (가장 큰 행렬의 범위를 최대 쿼리 개수인 10만 번 탐색하는 경우)
 * 시간 복잡도 : O (N)
 * 정확히는 O (Q). 쿼리의 개수만큼 작동
 * 이유 : 누적 합에서 범위의 합을 구하는 것과 비슷한 방식으로
 * 쿼리 개수만큼 O (1)로 작동해서 바로 결과를 얻어낼 수 있음
 * 알고리즘 : 누적 합
 * 자료구조 : Array
 * 풀이 방법 : 2차원 행렬을 3차원 누적 카운팅 배열을 통해서
 * 각 위치에 해당하는 정수의 누적 카운팅 개수를 구해둠
 * 그리고 쿼리에 받아온 정보를 통해 범위의 누적 합을 추출하듯
 * 누적 카운팅을 추출해서 서로 다른 정수의 개수를 셈
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14846 {
    static final int MAX_MATRIX_VALUE = 10; // 행렬에 들어가는 자연수 범위
    
    static int N; // 행렬의 크기
    static int[][] matrix; // 행렬 배열
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    
    static int[][][] prefixCnt; // 누적 카운팅 배열
    static void query(int x1, int y1, int x2, int y2) { // 쿼리의 정보를 바탕으로 서로 다른 번호의 개수를 세는 함수
        int differentCnt = 0; // 서로 다른 정수의 개수
        for (int n = 1; n <= MAX_MATRIX_VALUE; n++) { // 해당 범위의 누적 카운팅이 1개 이상인 경우
            if (prefixCnt[x2][y2][n] - prefixCnt[x1 - 1][y2][n]
                    - prefixCnt[x2][y1 - 1][n] + prefixCnt[x1 - 1][y1 - 1][n] >= 1) differentCnt++; // 카운팅
        }
        
        sb.append(differentCnt).append("\n"); // 결과값 추가하기
    } // query 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine()); // 행렬의 크기
        matrix = new int[N + 1][N + 1]; // 행렬 배열
        for (int x = 1; x <= N; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 1; y <= N; y++) {
                matrix[x][y] = Integer.parseInt(st.nextToken()); // 행렬 정보 입력
            }
        }
        
        prefixCnt = new int[N + 1][N + 1][MAX_MATRIX_VALUE + 1]; // 누적 카운팅 배열
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int n = 1; n <= MAX_MATRIX_VALUE; n++) { // 기존 숫자 누적 카운팅
                    prefixCnt[x][y][n] = prefixCnt[x - 1][y][n] + prefixCnt[x][y - 1][n] - prefixCnt[x - 1][y - 1][n];
                }
                
                prefixCnt[x][y][matrix[x][y]]++; // 현재 행렬 숫자 누적 카운팅
            }
        }
        
        int Q = Integer.parseInt(br.readLine()); // 쿼리의 개수
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken()); // x1 좌표
            int y1 = Integer.parseInt(st.nextToken()); // y1 좌표
            int x2 = Integer.parseInt(st.nextToken()); // x2 좌표
            int y2 = Integer.parseInt(st.nextToken()); // y2 좌표
            
            query(x1, y1, x2, y2); // 서로 다른 정수의 개수 찾기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료