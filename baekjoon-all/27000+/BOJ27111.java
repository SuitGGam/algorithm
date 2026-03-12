/*
 * 백준 27111번 : 출입 기록
 * https://www.acmicpc.net/problem/27111
 * 난이도 : 실버 5
 * 풀이 날짜 : 2026-03-12
 * 간단 설명 : 누락된 출입 기록의 취소 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ27111 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 출입 기록의 개수
        int[] record = new int[200000 + 1]; // 출입 기록 배열
        int cnt = 0; // 출입 기록의 최소 개수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int person = Integer.parseInt(st.nextToken()); // 출입하는 사람
            int move = Integer.parseInt(st.nextToken()); // 출입 여부

            if (move == 1) { // 들어가는 기록일 때
                if (record[person] == 0) { // 나간 상태일 때
                    record[person] = 1; // 들어와있는 상태로 변경
                } else { // 들어와있는 상태일 때
                    cnt++; // 나간 기록 하나 누락
                }
            } else { // 나가는 기록일 때
                if (record[person] == 1) { // 들어와있는 상태일 때
                    record[person] = 0; // 나간 상태로 변경
                } else { // 나가있는 상태일 때
                    cnt++; // 들어온 기록 하나 누락
                }
            }
        }

        // 버퍼 닫기
        br.close();

        for (int i = 1; i < record.length; i++) {
            if (record[i] == 1) cnt++; // 나간 기록 누락
        }

        // 결과값 출력하기
        System.out.print(cnt);
    } // main 종료
} // Main 종료