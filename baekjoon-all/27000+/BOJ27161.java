/*
 * 백준 27161번 : 크레이지 타임
 * https://www.acmicpc.net/problem/27161
 * 난이도 : 크레이지 타임
 * 풀이 날짜 : 2026-02-12
 * 간단 설명 : 게임에서 해야 하는 외쳐야 하는 시간과 해야 하는 행동을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ27161 {
    static class info { // 카드의 정보를 담을 class
        String clock; // 시계의 종류
        int time; // 시간

        info(String clock, int time) {
            this.clock = clock;
            this.time = time;
        }
    } // info 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 펼쳐질 카드의 개수
        info[] card = new info[N]; // 카드 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String clock = st.nextToken(); // 시계의 종류
            int time = Integer.parseInt(st.nextToken()); // 시간

            card[i] = new info(clock, time); // 카드 정보 저장
        }

        // 버퍼 닫기
        br.close();

        int curTime = 1; // 게임 내의 시간
        boolean opposite = false; // 시간의 진행 방향
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int i = 0; i < N; i++) {
            if (card[i].time == curTime && card[i].clock.equals("HOURGLASS")) { // 과부화의 원칙인 경우
                sb.append(curTime).append(" NO\n");
            } else if (card[i].time == curTime) { // 동기화의 법칙인 경우
                sb.append(curTime).append(" YES\n");
            } else if (card[i].clock.equals("HOURGLASS")) { // 시간 역행의 법칙인 경우
                sb.append(curTime).append(" NO\n");
                opposite = !opposite;
            } else sb.append(curTime).append(" NO\n"); // 아무것도 아닌 경우

            if (!opposite) {
                curTime++; // 시간 증가
                if (curTime == 13) curTime = 1;
            } else {
                curTime--; // 시간 감소
                if (curTime == 0) curTime = 12;
            }
        }

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료