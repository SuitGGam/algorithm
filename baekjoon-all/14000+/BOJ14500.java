/*
 * 백준 14500번 : 테트로미노
 * https://www.acmicpc.net/problem/14500
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-10-26
 * 간단 설명 : 각 테트로미노의 합 중 최댓값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N, M; // 종이의 세로 크기, 종이의 가로 크기
    static int maxSum; // 합의 최댓값
    static int[][] tetromino; // 종이 배열
    
    static int[][][][] shape = {
            {  // flat 모양
                    { {0, 0}, {0, 1}, {0, 2}, {0, 3} }, // 수평
                    { {0, 0}, {1, 0}, {2, 0}, {3, 0} }  // 수직
            }, // flat 종료
            {  // square 모양
                    { {0, 0}, {0, 1}, {1, 0}, {1, 1} }  // 정사각형
            }, // square 종료
            {  // L 모양
                    { {0, 0}, {1, 0}, {2, 0}, {2, 1} }, // L 모양
                    { {0, 0}, {0, 1}, {0, 2}, {1, 0} }, // 오른쪽으로  90도 회전한 L 모양
                    { {0, 0}, {0, 1}, {1, 1}, {2, 1} }, // 오른쪽으로 180도 회전한 L 모양
                    { {0, 2}, {1, 0}, {1, 1}, {1, 2} }, // 오른쪽으로 270도 회전한 L 모양
                    { {0, 1}, {1, 1}, {2, 0}, {2, 1} }, // L 대칭 모양
                    { {0, 0}, {1, 0}, {1, 1}, {1, 2} }, // 오른쪽으로  90도 회전한 L 대칭 모양
                    { {0, 0}, {0, 1}, {1, 0}, {2, 0} }, // 오른쪽으로 180도 회전한 L 대칭 모양
                    { {0, 0}, {0, 1}, {0, 2}, {1, 2} }  // 오른쪽으로 270도 회전한 L 대칭 모양
            }, // L 종료
            {  // zigzag 모양
                    { {0, 0}, {1, 0}, {1, 1}, {2, 1} }, // zigzag 모양
                    { {0, 1}, {0, 2}, {1, 0}, {1, 1} }, // 오른쪽으로 90도 회전한 zigzag 모양
                    { {0, 1}, {1, 0}, {1, 1}, {2, 0} }, // zigzag 대칭 모양
                    { {0, 0}, {0, 1}, {1, 1}, {1, 2} }  // 오른쪽으로 90도 회전한 zigzag 대칭 모양
            }, // zigzag 종료
            {  // T 모양
                    { {0, 0}, {0, 1}, {0, 2}, {1, 1} }, // T 모양
                    { {0, 1}, {1, 0}, {1, 1}, {2, 1} }, // 오른쪽으로  90도 회전한 T 모양
                    { {0, 1}, {1, 0}, {1, 1}, {1, 2} }, // 오른쪽으로 180도 회전한 T 모양
                    { {0, 0}, {1, 0}, {1, 1}, {2, 0} }  // 오른쪽으로 270도 회전한 T 모양
            }  // T 종료
    };
    
    static void calTetromino() { // 테트로미노 최댓값을 구하는 함수
        for (int t = 0; t < shape.length; t++) {
            for (int s = 0; s < shape[t].length; s++) {
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < M; y++) {
                        int tmpSum = 0; // 임시 합
                        boolean valid = true; // 4개가 다 더해졌는지 확인하는 boolean
                        
                        for (int d = 0; d < 4; d++) {
                            int nx = x + shape[t][s][d][0];
                            int ny = y + shape[t][s][d][1];
                            
                            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                                valid = false; // 4개가 다 더해지지 않음
                                break; // 범위를 벗어나면 break
                            }
                            
                            tmpSum += tetromino[nx][ny];
                        }
                        
                        if (valid && maxSum < tmpSum) maxSum = tmpSum; // 최댓값 갱신
                    }
                }
            }
        }
    } // calTetromino 종료
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 종이의 세로 크기
        M = Integer.parseInt(st.nextToken()); // 종이의 가로 크기
        tetromino = new int[N][M]; // 종이 배열 크기 지정
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int y = 0; y < M; y++) {
                tetromino[x][y] = Integer.parseInt(st.nextToken()); // 수 저장
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        maxSum = 0; // 점수 최댓값 초기화
        calTetromino(); // 테트로미노 시작
        
        // 결과값 출력하기
        System.out.print(maxSum);
    } // main 종료
} // Main 종료