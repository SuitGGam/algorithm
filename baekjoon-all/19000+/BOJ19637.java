/*
 * 백준 19637번 : IF문 좀 대신 써줘
 * https://www.acmicpc.net/problem/19637
 * 난이도 : 실버 3
 * 풀이 날짜 : 2026-03-15
 * 간단 설명 : 주어지는 IF의 조건에 맞게 전투력을 구분하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ19637 {
    static int N; // 칭호의 수
    static String[] title; // 칭호 배열
    static int[] power; // 전투력 배열

    static String binarySearch(int combatPower) { // 전투력에 맞는 칭호를 찾는 문제
        int left = 0; // 왼쪽 포인터
        int right = N - 1; // 오른쪽 포인터
        String result = ""; // 칭호
        while (left <= right) {
            int mid = (left + right) / 2; // 중간 포인터

            if (combatPower <= power[mid]) { // 포인터가 있는 전투력보다 낮거나 같은 경우
                result = title[mid]; // 칭호 갱신
                right = mid - 1; // 오른쪽 포인터 당기기
            } else { // 포인터가 있는 전투력보다 높은 경우
                left = mid + 1;
            }
        }

        return result; // 칭호 반환
    } // binarySearch 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 칭호의 수
        int M = Integer.parseInt(st.nextToken()); // 캐릭터의 수

        title = new String[N]; // 칭호 배열
        power = new int[N]; // 전투력 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            title[i] = st.nextToken(); // 칭호 입력
            power[i] = Integer.parseInt(st.nextToken()); // 전투력 입력
        }

        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0; i < M; i++) {
            int combatPower = Integer.parseInt(br.readLine()); // 캐릭터의 전투력
            String result = binarySearch(combatPower); // 받은 칭호

            sb.append(result).append("\n"); // 결과값 추가하기
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료