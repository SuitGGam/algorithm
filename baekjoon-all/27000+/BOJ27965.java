/*
 * 백준 27965번 : N결수
 * https://www.acmicpc.net/problem/27965
 * 난이도 : 실버 3
 * 풀이 날짜 : 2026-01-03
 * 간단 설명 : N결수를 K로 나눈 나머지를 구하는 문제
 */

/*
 * 완전 탐색 경우의 수 : N (10,000,000)
 * 이유 : 1부터 N까지 전부 다 합친 다음 K로 나눈 나머지를 구하면 됨
 *
 * 알고리즘 : 모듈러 연산
 * 자료구조 : X
 * 시간 복잡도 : O (N)
 * 풀이 방법 : 1부터 N까지 모두 연결하게 되면
 * 우리가 흔히 사용하는 자료형인 int와 long에서 처리가 불가능함
 * 길이가 말도 안 되게 길어짐
 * 다음에 올 숫자의 길이만큼 현재 남은 수에 곱한 다음 다음 숫자를 더하는 방식으로 진행
 * 현재 수가 11이고 다음 수가 12면 11 x 10^2 (12의 문자열 길이는 2)만큼 하면
 * 1100이 되고 다음 수(12)를 더하고 K로 나누면 됨
 * 그러면 우리가 사용하는 자료형에서 처리가 가능함
 * 그리고 모듈려 연산의 특징을 이용해서 매번 % K를 해줘야 함
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ27965 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int N = Integer.parseInt(st.nextToken()); // N결수
        long K = Long.parseLong(st.nextToken()); // 나눌 값
        
        // 버퍼 닫기
        br.close();
        
        long ans = 0;
        for (long i = 1; i <= N; i++) {
            ans += i;
            if (ans >= K) ans %= K;
            if (i == N) break;
            ans *= (long) Math.pow(10, (long) Math.log10(i + 1) + 1);
        }
        
        // 결과값 출력하기
        System.out.print(ans);
    } // main 종료
} // Main 종료