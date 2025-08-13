/*
 * 백준 25192번 : 인사성 밝은 곰곰이
 * https://www.acmicpc.net/problem/25192
 * 난이도 : 실버 4
 * 풀이 날짜 : 2025-08-13
 * 간단 설명 : 채팅방에서 곰곰 이모티콘을 몇 번 사용했는지 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ25192 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 채팅방의 기록 수

        HashSet<String> chat = new HashSet<>(); // 채팅한 사람의 기록을 나타낼 해시셋
        int useGom = 0; // 곰곰티콘이 사용된 횟수
        for (int i = 0; i < N; i++) {
            String nickname = br.readLine(); // 채팅

            if (nickname.equals("ENTER")) { // 새로 입장한 경우
                useGom += chat.size(); // 채팅했던 사람만큼 추가
                chat.clear(); // 채팅 기록 초기화
            } else { // 채팅을 친 경우
                chat.add(nickname);
            }
        }

        // 버퍼 닫기
        br.close();

        useGom += chat.size(); // 마지막에 한 번 더 더해주기

        // 결과값 출력하기
        System.out.print(useGom);
    } // main 종료
} // Main 종료