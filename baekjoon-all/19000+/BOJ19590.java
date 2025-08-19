/*
 * 백준 19590번 : 비드맨
 * https://www.acmicpc.net/problem/19590
 * 난이도 : 골드 2
 * 풀이 날짜 : 2025-08-19
 * 간단 설명 : 비드맨이 최대한 구슬을 많이 없앴을 때 남는 구슬의 개수를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ19590 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 버퍼 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        
        // 테스트 케이스 입력받기
        int testCase = Integer.parseInt(br.readLine());
        int[] beads  = new int[testCase];
        long sum = 0;
        for (int i = 0; i < beads.length; i++) {
            beads[i] = Integer.parseInt(br.readLine());
            sum += beads[i];
        }
        
        // 버퍼 닫기
        br.close();
        
        // 배열 정렬하기
        Arrays.sort(beads);
        
        // 구슬대전 배틀 비드맨
        // 배열의 최댓값이 나머지의 합보다 클 때
        // 배열의 최댓값이 나머지의 합보다 작을 때
        long result = 0;
        if (beads[beads.length - 1] >= sum - beads[beads.length - 1]) {
            for (int i = beads.length - 1; i > 0; i--) {
                if (beads[i] == 0) continue;
                for (int j = i - 1; j >= 0; j--) {
                    if (beads[i] >= beads[j]) {
                        beads[i] -= beads[j];
                        beads[j]  = 0;
                    } else {
                        beads[j] -= beads[i];
                        beads[i]  = 0;
                    }
                }
            }
            
            for (int i = 0; i < beads.length; i++) {
                result += beads[i];
            }
        } else {
            if (sum % 2 == 0) {
                result = 0;
            } else {
                result = 1;
            }
        }
        
        // 결과값 출력하기
        System.out.println(result);
    }
}