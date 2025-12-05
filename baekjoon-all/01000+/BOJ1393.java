/*
 * 백준 1393번 : 음하철도 구구팔
 * https://www.acmicpc.net/problem/1393
 * 난이도 : 실버 1393
 * 풀이 날짜 : 2025-12-06
 * 간단 설명 : 철도에서 정류장까지의 가장 가까운 위치를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1393 {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int xs = Integer.parseInt(st.nextToken());
        int ys = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine(), " ");
        int xe = Integer.parseInt(st.nextToken());
        int ye = Integer.parseInt(st.nextToken());
        int dx = Integer.parseInt(st.nextToken());
        int dy = Integer.parseInt(st.nextToken());
        
        // 버퍼 닫기
        br.close();
        
        // dx, dy 최소공배수로 나누기
        for (int i = 100; i >= 1; i--) {
            if (Math.abs(dx) % i == 0 && Math.abs(dy) % i == 0){
                dx /= i;
                dy /= i;
                break;
            }
        }
        
        long x = xe;
        long y = ye;
        for (int i = 1; i <= 100; i++) {
            long nx = xe + dx * i;
            long ny = ye + dy * i;
            
            if((xs - x) * (xs - x) + (ys - y) * (ys - y) > (xs - nx) * (xs - nx) + (ys - ny) * (ys - ny)) {
                x = nx;
                y = ny;
            }
        }
        
        // 결과값 출력하기
        System.out.print(x + " " + y);
    } // main 종료
} // Main 종료