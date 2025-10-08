/*
 * 백준 1992번 : 쿼드트리
 * https://www.acmicpc.net/problem/1992
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-10-09
 * 간단 설명 : 영상을 압축한 결과를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static char[][] video; // 영상 배열
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
    static void quadTree(int x, int y, int size) { // 쿼드트리를 구하는 함수
        if (isCompressible(x, y, size)) { // 압축이 가능한 경우
            sb.append(video[x][y]); // 결과값 추가하기
            return;
        }
        
        // 압축이 불가능한 경우
        int halfSize = size / 2; // 사이즈 반절로 나누기
        
        sb.append("("); // 여는 괄호 추가하기
        
        quadTree(x, y, halfSize); // 왼쪽 위
        quadTree(x, y + halfSize, halfSize); // 오른쪽 위
        quadTree(x + halfSize, y, halfSize); // 왼쪽 아래
        quadTree(x + halfSize, y + halfSize, halfSize); // 오른쪽 아래
        
        sb.append(")"); // 닫는 괄호 추가하기
    } // quadTree 종료
    
    static boolean isCompressible(int x, int y, int size) { // 압축이 가능한지 확인하는 함수
        int sameValue = video[x][y]; // 같은지 확인할 영상의 값
        
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (sameValue != video[i][j]) return false; // 압축이 불가능한 경우
            }
        }
        
        return true; // 압축이 가능한 경우
    } // isCompressible 종료
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 영상의 크기
        video = new char[N][N]; // 배열 크기 지정
        for (int x = 0; x < N; x++) {
            String info = br.readLine(); // 영상의 정보
            for (int y = 0; y < N; y++) {
                video[x][y] = info.charAt(y); // 영상 정보 저장
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        quadTree(0, 0, N); // 쿼드트리 구하기
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료