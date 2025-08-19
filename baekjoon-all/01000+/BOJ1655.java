/*
 * 백준 1655번 : 가운데를 말해요
 * https://www.acmicpc.net/problem/1655
 * 난이도 : 골드 2
 * 풀이 날짜 : 2025-08-20
 * 간단 설명 : 숫자가 계속 누적으로 쌓일 때 가운데 있는 값이 뭔지 찾는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ1655 {
    // 입력을 위한 객체 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    // 최소 힙, 최대 힙
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 백준이가 외치는 정수의 개수
        
        StringBuilder sb = new StringBuilder(); // 결과값을 저장하기 위한 객체
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine()); // 백준이가 외치는 수
            
            // 최소 힙과 최대 힙을 반대로 넣을 예정
            // 그래야 중간값이 바로바로 나옴
            if (maxHeap.isEmpty() || number < maxHeap.peek()) maxHeap.add(number);
            else minHeap.add(number);
            
            // 최대 힙이 최소 힙보다 1개 더 많거나 같게 유지해야 함
            // 그래야 중간값 유지
            if (maxHeap.size() < minHeap.size()) maxHeap.add(minHeap.poll());
            else if (maxHeap.size() > minHeap.size() + 1) minHeap.add(maxHeap.poll());
            
            // 결과값 추가하기
            if (maxHeap.size() >= minHeap.size()) sb.append(maxHeap.peek()).append("\n");
            else sb.append(minHeap.peek()).append("\n");
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기ㅏ
        System.out.print(sb);
    } // main 종료
} // Main 종료