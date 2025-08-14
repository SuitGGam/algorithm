/*
 * 백준 2304번 : 창고 다각형
 * https://www.acmicpc.net/problem/2304
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-08-14
 * 간단 설명 : 다각형 창고의 면적을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ2304 {
    // 입력을 위한 객체 선언
    static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine()); // 기둥의 개수
        storage = new int[N][2]; // 기둥 몇 개인지 지정
        int maxLocation = - 1; // 가종 높은 기둥의 위치
        int maxHeight = -1; // 가장 높은 기둥의 높이
        for (int i = 0; i < N; i++) { // 기둥 정보 입력 시작
            st = new StringTokenizer(br.readLine(), " ");
            storage[i][0] = Integer.parseInt(st.nextToken()); // 기둥 위치
            storage[i][1] = Integer.parseInt(st.nextToken()); // 기둥 높이
            // 가장 높은 기둥의 위치, 높이 갱신
            if (maxHeight < storage[i][1]) {
                maxLocation = storage[i][0];
                maxHeight = storage[i][1];
            }
        } // 기둥 정보 입력 종료

        // 버퍼 닫기
        br.close();

        // 위치 기준으로 오름차순 정렬
        // 그래야 실제 모양대로 바껴서 계산이 편해짐
        Arrays.sort(storage, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        // 가장 높은 기둥의 인덱스 찾기
        int idx = -1;
        for (int i = 0; i < N; i++) {
            if (storage[i][0] == maxLocation && storage[i][1] == maxHeight) {
                idx = i;
                break;
            }
        }

        // 면적 구하기
        area = 0; // 면적 초기화
        calArea(maxLocation, maxHeight, idx);

        // 결과값 출력하기
        System.out.print(area);
    } // main 종료

    static int N; // 기둥의 개수
    static int area; // 창고의 면적
    static int idx;  // 최고 높이 기둥 인덱스(위치 오름차순 정렬 시)
    static int[][] storage; // 창고 기둥 높이 배열

    // 면적 구하는 함수
    static void calArea(int location, int height, int idx) {
        area += height; // 최고 높이 지점(면적 1칸) 높이 더하기
        calLeftArea(location, 0, idx - 1);  // 왼쪽 다음 최고 높이 구하기
        calRightArea(location, 0, idx + 1); // 오른쪽 다음 최고 높이 구하기
    }

    // 왼쪽 최고 높이 구하는 함수
    static void calLeftArea(int location, int height, int idx) {
        // 왼쪽 모든 구간을 구했다면 종료
        if (idx == -1) return;

        // 현재 구간에서 최고 높이 찾기
        int newIdx = -1; // 현재 구간 최고 높이 기둥의 인덱스
        for (int i = 0; i <= idx; i++) {
            if (height < storage[i][1]) {
                height = storage[i][1];
                newIdx = i;
            }
        }

        // 이전 최대 높이 구간에서
        // 현재 최대 높이 구간까지
        // 면적 더해주기
        area += (location - storage[newIdx][0]) * height;

        calLeftArea(storage[newIdx][0], 0, newIdx - 1); // 왼쪽 다음 최고 높이 구하기
    }

    // 오른쪽 최고 높이 구하는 함수
    static void calRightArea(int location, int height, int idx) {
        // 오른쪽 모든 구간을 구했다면 종료
        if (idx == N) return;

        // 현재 구간에서 최고 높이 찾기
        int newIdx = -1; // 현재 구간 최고 높이 기둥의 인덱스
        for (int i = idx; i < N; i++) {
            if (height < storage[i][1]) {
                height = storage[i][1];
                newIdx = i;
            }
        }

        // 이전 최대 높이 구간에서
        // 현재 최대 높이 구간까지
        // 면적 더해주기
        area += (storage[newIdx][0] - location) * height;

        calRightArea(storage[newIdx][0], 0, newIdx + 1); // 오른쪽 다음 최고 높이 구하기
    }
} // class 종료