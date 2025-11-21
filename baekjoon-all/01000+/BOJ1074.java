/*
 * 백준 1074번 : Z
 * https://www.acmicpc.net/problem/1074
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-11-22
 * 간단 설명 : Z모양으로 이동 시, R행 C열에 몇 번째로 도착하는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()); // 행
        int c = Integer.parseInt(st.nextToken()); // 열
        
        // 버퍼 닫기
        br.close();
        
        int size = (int) Math.pow(2, N); // 한 변의 크기
        int orderOfVisit = 0; // 방문 순서
        while (size > 1) {
            if (r < size / 2 && c < size / 2) { // 왼쪽 위
                size /= 2;
            } else if (r < size / 2 && c >= size / 2) { // 오른쪽 위
                orderOfVisit += size * size / 4;
                size /= 2;
                c -= size;
            } else if (r >= size / 2 && c < size / 2) { // 왼쪽 아래
                orderOfVisit += size * size / 4 * 2;
                size /= 2;
                r -= size;
            } else { // 오른쪽 아래
                orderOfVisit += size * size / 4 * 3;
                size /= 2;
                r -= size;
                c -= size;
            }
        }
        
        // 결과값 출력하기
        System.out.print(orderOfVisit);
    } // main 종료
} // Main 종료