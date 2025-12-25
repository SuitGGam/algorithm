/*
 * 백준 30406번 : 산타 춘배의 선물 나눠주기
 * https://www.acmicpc.net/problem/30406
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-12-26
 * 간단 설명 : 아기 고양이들에게 선물을 나눠줬을 때 가장 큰 만족도를 구하는 문제
 */

/*
 * 최대 경우의 수 : (N - 1)!!
 * 이유 : 모든 쌍에 대해서 조합을 골라내야 함
 * 시간 복잡도 : O (N)
 * 알고리즘 : 그리디
 * 자료구조 : X
 * 풀이 방법 : XOR 가격 환산표
 * 같은 가격끼리는 만족도 0점
 * 01 : 1점, 02 : 2점, 03 : 3점
 * 12 : 3점, 13 : 2점, 23 : 1점
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ30406 {
    static int zero, one, two, three; // 0, 1, 2, 3의 개수
    
    static int hand() { // 선물을 나눠주는 함수
        int maxSatisfaction = 0; // 최대 만족도
        int cnt;
        
        // 가장 큰 만족도(3점) 더하기
        // 03, 12
        cnt = Math.min(zero, three);
        maxSatisfaction += cnt * 3;
        zero -= cnt;
        three -= cnt;
        
        cnt = Math.min(one, two);
        maxSatisfaction += cnt * 3;
        one -= cnt;
        two -= cnt;
        
        // 중간 만족도(2점) 더하기
        // 02, 13
        cnt = Math.min(zero, two);
        maxSatisfaction += cnt * 2;
        zero -= cnt;
        two -= cnt;
        
        cnt = Math.min(one, three);
        maxSatisfaction += cnt * 2;
        one -= cnt;
        three -= cnt;
        
        // 가장 작은 만족도(1점) 더하기
        // 01, 23
        cnt = Math.min(zero, one);
        maxSatisfaction += cnt;
        zero -= cnt;
        one -= cnt;
        
        cnt = Math.min(two, three);
        maxSatisfaction += cnt;
        two -= cnt;
        three -= cnt;
        
        return maxSatisfaction; // 최대 만족도 return
    } // hand 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 선물의 개수
        zero = one = two = three = 0; // 0, 1, 2, 3의 개수 초기화
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int price = Integer.parseInt(st.nextToken()); // 선물의 가격 저장
            if (price == 0) zero++;
            else if (price == 1) one++;
            else if (price == 2) two++;
            else three++;
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(hand());
    } // main 종료
} // Main 종료