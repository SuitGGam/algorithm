/*
 * 백준 1874번 : 스택 수열
 * https://www.acmicpc.net/problem/1874
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-08-05
 * 간단 설명 : 스택으로 만들 수 있는 수열인지 확인하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1874 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine()); // 정수 n

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        int curNumber = 1; // 스택에 들어갈 숫자
        boolean valid = true; // 수열의 유효성 확인
        int[] stack = new int[n]; // 스택 역할을 할 배열
        int top = -1; // 스택의 최상단을 가리키는 변수
        for (int i = 0; i < n; i++) {
            int popNumber = Integer.parseInt(br.readLine()); // pop 해야 할 숫자

            // 스택에 들어갈 숫자가 pop 해야 할 숫자보다
            // 작거나 같은 경우 push
            while (curNumber <= popNumber) {
                stack[++top] = curNumber++; // push 하기
                sb.append("+").append("\n"); // 결과값 추가하기
            }

            if (stack[top] == popNumber) { // stack의 top과 popNumber가 같으면 pop
                top--; // pop 하기
                sb.append("-").append("\n"); // 결과값 추가하기
            } else { // 같지 않으면 유효하지 않은 수열
                valid = false;
                break;
            }

        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        if (valid) System.out.print(sb);
        else System.out.print("NO");
    } // main 종료
} // Main 종료