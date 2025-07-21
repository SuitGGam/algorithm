/*
 * 백준 14753번 : MultiMax
 * https://www.acmicpc.net/problem/14753
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-07-22
 * 간단 설명 : 주어진 카드값 중 2개 혹은 3개를 골라서 곱이 가장 큰 값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ14753 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine()); // 카드의 수
        
        int[] cards = new int[n]; // 카드를 담을 배열
        int positiveCnt = 0; // 양수의 개수
        int negativeCnt = 0; // 음수의 개수
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken()); // 카드값 저장
            if (cards[i] > 0) positiveCnt++; // 양수 개수 증가
            if (cards[i] < 0) negativeCnt++; // 음수 개수 증가
        }
        
        // 버퍼 닫기
        br.close();
        
        // 카드값 비내림차순 정렬
        Arrays.sort(cards);
        
        int multiMax = 0; // 최대곱
        
        // 음수가 2개 이상인 경우
        if (negativeCnt >= 2) {
            // 양수가 3개 이상인 경우
            if (positiveCnt >= 3) {
                int threePositive  = cards[n - 1] * cards[n - 2] * cards[n - 3]; // 가장 큰 양수 3개의 곱
                int onePosiTwoNega = cards[n - 1] * cards[0] * cards[1]; // 가장 작은 음수 2개와 가장 큰 양수의 곱
                multiMax = (threePositive >= onePosiTwoNega ? threePositive : onePosiTwoNega);
            }
            // 양수가 3개 미만인 경우
            else {
                // 양수가 0개인 경우
                if (positiveCnt == 0) multiMax = cards[0] * cards[1];
                    // 양수가 1개인 경우
                else if (positiveCnt == 1) multiMax = cards[0] * cards[1] * cards[n - 1];
                    // 양수가 2개인 경우
                else {
                    int twoPositive    = cards[n - 1] * cards[n - 2]; // 가장 큰 양수 2개의 곱
                    int onePosiTwoNega = cards[0] * cards[1] * cards[n - 1]; // 가장 작은 음수 2개와 가장 큰 양수의 곱
                    multiMax = (twoPositive >= onePosiTwoNega ? twoPositive : onePosiTwoNega);
                }
            }
        }
        
        // 음수가 2개 미만인 경우
        else {
            int twoBig = cards[n - 1] * cards[n - 2]; // 가장 큰 수 2개를 곱한 값
            
            // 음수가 1개면서 n이 3인 경우
            if (negativeCnt == 1 && n == 3) {
                multiMax = twoBig;
            }
            
            // 음수가 0개인 경우
            else {
                multiMax = twoBig;
                // 세 번째로 큰 숫자가 0보다 크면 곱하기
                if (cards[n - 3] > 0) multiMax *= cards[n - 3];
            }
        }
        
        // 결과값 출력하기
        System.out.print(multiMax);
    } // main 종료
} // Main 종료