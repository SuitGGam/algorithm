/*
 * 백준 1541번 : 잃어버린 괄호
 * https://www.acmicpc.net/problem/1541
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-10-25
 * 간단 설명 : 식에 임의의 괄호를 쳐서 최솟값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1541 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        // + 끼리 더한 다음에 빼줘야 큰 값을 뺄 수 있어서 - 단위로 분류
        String[] plus = br.readLine().split("-");
        
        // 버퍼 닫기
        br.close();
        
        for (int i = 0; i < plus.length; i++) {
            if (plus[i].contains("+")) { // +를 포함한 경우
                String[] num = plus[i].split("\\+");
                int sum = 0;
                for (int j = 0; j < num.length; j++) sum += Integer.parseInt(num[j]);
                
                plus[i] = String.valueOf(sum);
            }
        }
        
        int min = Integer.parseInt(plus[0]); // 최솟값
        for (int i = 1; i < plus.length; i++) min -= Integer.parseInt(plus[i]);
        
        // 결과값 출력하기
        System.out.print(min);
    } // main 종료
} // Main 종료