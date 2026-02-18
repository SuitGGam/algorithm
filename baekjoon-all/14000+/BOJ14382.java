/*
 * 백준 14382번 : 숫자세는 양 (Large)
 * https://www.acmicpc.net/problem/14382
 * 난이도 : 실버 4
 * 풀이 날짜 : 2026-02-18
 * 간단 설명 : 블리트릭스가 잠에 빠지는 수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class BOJ14382 {
    static long tmp; // 임시 숫자
    static boolean[] record = new boolean[10]; // 적어놓은 숫자들을 기록할 배열
    static HashMap<Long, Integer> map = new HashMap<>(); // 숫자 중복 여부를 파악할 HashMap
    static StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체

    static void sleep(int tc, long N, long i, long original) { // 블리트릭스가 잠에 빠지는 수를 찾는 함수
        if (map.containsKey(N)) { // 잠에 드는 게 불가능한 경우
            sb.append("Case #").append(tc).append(": INSOMNIA\n"); // 결과값 추가하기
            return; // 종료
        }

        map.put(N, 1); // 중복 방지를 위한 N 저장
        while (tmp > 0) {
            record[(int) (tmp % 10)] = true; // 숫자 적기
            tmp /= 10; // 기록한 숫자 버리기
        }

        if (valid()) { // 잠에 들 수 있는 경우
            sb.append("Case #").append(tc).append(": ").append(N).append("\n"); // 결과값 추가하기
            return; // 종료
        }

        tmp = (i + 1) * original; // 다음 숫자 떠올리기
        sleep(tc, tmp, i + 1, original); // 다음 기록 시작
    } // sleep 종료

    static boolean valid() { // 수면이 가능한지 확인하는 함수
        for (int i = 0; i < 10; i++) {
            if (!record[i]) return false; // 잠에 들 수 없음
        }

        return true; // 잠에 들 수 있음
    } // valid 종료

    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(br.readLine()); // 블리트릭스가 고른 숫자
            tmp = N;
            Arrays.fill(record, false); // 기록 배열 초기화
            map.clear(); // map 비우기
            sleep(tc, N, 1, N); // 잠에 빠지는 수 찾기
        }

        // 버퍼 닫기
        br.close();

        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료