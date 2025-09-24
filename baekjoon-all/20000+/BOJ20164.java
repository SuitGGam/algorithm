/*
 * 백준 20164번 : 홀수 홀릭 호석
 * https://www.acmicpc.net/problem/20164
 * 난이도 : 골드 5
 * 풀이 날짜 : 2025-09-25
 * 간단 설명 : 홀수 홀릭을 했을 때 찾을 수 있는 최대 홀수의 개수와 최소 홀수의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20164 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int minOdd, maxOdd; // 최소 홀수 개수, 최대 홀수 개수
    static void oddHolic(int N, int cnt) { // 숫자를 커팅하면서 홀릭을 하는 함수
        cnt += countOdd(N); // 처음 기준 홀수 개수 세기

        if (N / 10 == 0) { // 숫자가 한 자리인 경우
            minOdd = Math.min(minOdd, cnt); // 최소 개수 갱신
            maxOdd = Math.max(maxOdd, cnt); // 최대 개수 갱신
        } else if (N / 100 == 0) { // 숫자가 두 자리인 경우
            int next = N / 10; // 둘째 자리 수 구하기
            next += N % 10; // 첫째 자리 수 더하기

            oddHolic(next, cnt); // 다음 숫자 쪼개기 호출
        } else { // 숫자가 세 자리인 경우
            String num = String.valueOf(N); // 숫자를 분할하기 쉽게 문자열로 변환
            for (int i = 0; i < num.length() - 2; i++) { // 숫자 첫 번째 분할
                for (int j = i + 1; j < num.length() - 1; j++) { // 숫자 두 번째 분할
                    int next = Integer.parseInt(num.substring(0, i + 1)); // 첫 번째 분할 자리 더하기
                    next += Integer.parseInt(num.substring(i + 1, j + 1)); // 두 번째 분할 자리 더하기
                    next += Integer.parseInt(num.substring(j + 1)); // 세 번째 분할 자리 더하기

                    oddHolic(next, cnt); // 다음 숫자 쪼개기 호출
                }
            }
        }
    } // oddHolic 종료

    static int countOdd(int N) { // 홀수의 개수를 세는 함수
        int cnt = 0; // 홀수의 개수
        while (N > 0) {
            int tmp = N % 10; // 마지막 숫자
            if (tmp % 2 == 1) cnt++; // 홀수면 카운트
            N /= 10; // 카운트한 숫자는 버리기
        }

        return cnt; // 홀수의 개수 반환
    } // countOdd 종료

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine()); // 호석이가 가지고 있는 수

        // 버퍼 닫기
        br.close();

        minOdd = Integer.MAX_VALUE; // 최소 홀수 개수 초기화
        maxOdd = 0; // 최대 홀수 개수 초기화
        oddHolic(N, 0); // 홀수 홀릭

        // 결과값 출력하기
        System.out.print(minOdd + " " + maxOdd);
    } // main 종료
} // Main 종료