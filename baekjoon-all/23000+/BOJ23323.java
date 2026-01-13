/*
 * 백준 23323번 : 황소 다마고치
 * https://www.acmicpc.net/problem/23323
 * 난이도 : 실버 2
 * 풀이 날짜 : 2026-01-12
 * 간단 설명 : 황소의 현재 체력과 먹이의 개수가 주어질 때 최대 얼마나 생존 가능한지를 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : T * 2^생존일 수 (1,000 x 2^?)
 * 이유 : 며칠을 생존할지는 모르지만 생존하는 동안 매일 고민해야 함
 * 먹이를 줄지 말지 선택을 해야 하고 해당 먹이를 얼마큼 줄지도 정해야 함
 * 경우의 수는 무수히 많음
 *
 * 알고리즘 : 수학
 * 자료구조 : X
 * 시간 복잡도 : O (T * logN)
 * 풀이 방법 : 수학 같아서 수학이라고 쓰긴 했지만 수학적 이해는 모르겠음
 * 처음에 봤을 때 직관적으로 떠올린 건 먹이를 홀수일 때 1씩 줘야 한다는 것이었음
 * 이유는 홀수 상태로 2로 나누면 소숫점을 버리기 때문에 짝수일 때보다 더 큰 손해가 발생함
 * 그렇게 여러 테스트 케이스를 만들고 먹이도 여러 방법으로 줘봄
 * 어떻게 주든 마지막에 같은 점이 있었음
 * 결국 체력이 1인 상황이 온다는 것임
 * 체력이 1일 때 먹이를 1씩만 주면서 하루씩 연장을 하는 것이 최선이었음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ23323 {
    static long whenDie(long n, long m) { // 죽을 때까지 며칠이 걸리는지 구하는 함수
        long day = 0; // 죽을 때까지 걸리는 일수
        while (n > 1) { // n이 1이 될 때까지 2로 나누기
            day++;
            n /= 2;
        }
        
        day += m + 1; // 남은 먹이 + 1만큼 일수 증가 (하루에 하나씩만 주면 됨)
        
        return day; // 죽을 때까지 걸리는 일수 반환
    } // whenDie 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            long n = Long.parseLong(st.nextToken()); // 황소의 초기 체력
            long m = Long.parseLong(st.nextToken()); // 가진 먹이의 개수
            
            long day = whenDie(n, m); // 죽을 때까지 걸리는 일수
            sb.append(day).append("\n"); // 결과값 추가하기
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료