/*
 * 백준 14626번 : ISBN
 * https://www.acmicpc.net/problem/14626
 * 난이도 : 브론즈 1
 * 풀이 날짜 : 2025-07-20
 * 간단 설명 : ISBN의 훼손된 숫자를 복원하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ14626 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        String ISBN = br.readLine(); // ISBN 번호
        
        // 버퍼 닫기
        br.close();
        
        int sum = 0; // ISBN 일렬번호의 합
        int checkStar = -1; // 훼손된 숫자 인덱스
        int len = ISBN.length(); // ISBN 길이
        for (int i = 0; i < len - 1; i++) {
            // ISBN 1 ~ 12번째 자리의 합 구하기
            char ch = ISBN.charAt(i); // 현재 자리 숫자
            if (ch == '*') checkStar = i; // 훼손된 자리 체크
            else { // 훼손되지 않은 경우
                sum += (ch - '0') * (i % 2 == 0 ? 1 : 3);
            }
        }
        
        // 체크기호 더하기
        sum += ISBN.charAt(len - 1) - '0';
        
        // 훼손된 숫자 확인하기
        // 훼손된 숫자가 홀수 번째 자리인 경우
        int damaged = -1; // 훼손된 숫자
        if (checkStar % 2 != 0) {
            for (int i = 0; i < 10; i++) {
                if ((sum + (i * 3)) % 10 == 0) {
                    damaged = i;
                    break;
                }
            }
        }
        // 훼손된 숫자가 짝수 번째 자리인 경우
        else damaged = 10 - (sum % 10);
        
        // 결과값 출력하기
        System.out.print(damaged);
    } // main 종료
} // Main 종료