/*
 * 백준 1337번 : 올바른 배열
 * https://www.acmicpc.net/problem/1337
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-10-22
 * 간단 설명 : 연속된 배열이 되려면 추가 원소가 몇 개 필요한지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1337 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 배열의 크기
        int[] arr = new int[N]; // 배열 생성
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine()); // 원소 저장
        
        // 버퍼 닫기
        br.close();
        
        Arrays.sort(arr); // 비내림차순 정렬
        int needElement = 4; // 추가로 필요한 원소
        for (int i = 0; i < N; i++) {
            int curNum = arr[i]; // 현재 번호
            int correct = 0; // 연속된 원소 개수
            for (int j = i + 1; j < i + 5; j++) {
                if (j >= N) break; // 범위를 벗어나면 종료
                if (curNum >= arr[j] - 4) correct++; // 연속된 원소면 추가
            }
            
            if (needElement > 4 - correct) needElement = 4 - correct; // 필요한 원소 개수 갱신
        }
        
        // 결과값 출력하기
        System.out.print(needElement);
    } // main 종료
} // Main 종료