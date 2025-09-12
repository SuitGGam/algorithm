/*
 * 백준 30021번 : 순열 선물하기
 * https://www.acmicpc.net/problem/30021
 * 난이도 : 실버 2
 * 풀이 날짜 : 2025-09-12
 * 간단 설명 : 선물을 만족하는 순열 구하기 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ30021 {
    // 출력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 정수 N
        
        StringBuilder sb = new StringBuilder();
        // 정답이 불가능한 경우
        if (N == 2) {
            sb.append("NO");
        }
        // 정답인데 예외인 경우
        else if(N == 1 || N == 3) {
            sb.append("YES").append("\n");
            sb.append(1).append(" ");
            if (N == 3) {
                sb.append(3).append(" ").append(2);
            }
        }
        // 정답이면서 N이 4 이상인 경우
        else {
            sb.append("YES").append("\n");
            sb.append(4).append(" ").append(2).append(" ").append(3).append(" ").append(1).append(" ");
            for (int i = 5; i <= N; i++) {
                sb.append(i).append(" ");
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    }
}