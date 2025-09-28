/*
 * 백준 23348번 : 스트릿 코딩 파이터
 * https://www.acmicpc.net/problem/23348
 * 난이도 : 브론즈 3
 * 풀이 날짜 : 2025-09-28
 * 간단 설명 : 가장 높은 점수를 받은 동아리의 점수를 구하는 문제 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ23348 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken()); // 한손 코딩 난이도
        int B = Integer.parseInt(st.nextToken()); // 노룩 코딩 난이도
        int C = Integer.parseInt(st.nextToken()); // 폰 코딩 난이도

        int maxScore = 0; // 동아리의 최고 점수
        int N = Integer.parseInt(br.readLine()); // 참가한 동아리의 수
        for (int i = 0; i < N; i++) {
            int tmpScore = 0; // 임시 동아리 점수
            for (int j = 0; j < 3; j++) {
                st =  new StringTokenizer(br.readLine());
                tmpScore += Integer.parseInt(st.nextToken()) * A;
                tmpScore += Integer.parseInt(st.nextToken()) * B;
                tmpScore += Integer.parseInt(st.nextToken()) * C;
            }

            if (maxScore < tmpScore) maxScore = tmpScore; // 최고 점수 갱신
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(maxScore);
    } // main 종료
} // Main 종료