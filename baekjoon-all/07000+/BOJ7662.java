/*
 * 백준 7662번 : 이중 우선순위 큐
 * https://www.acmicpc.net/problem/7662
 * 난이도 : 골드 4
 * 풀이 날짜 : 2025-11-23
 * 간단 설명 : 이중 우선순위 큐를 구현해서 쿼리가 끝났을 때 최댓값, 최솟값을 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.HashMap;

public class BOJ7662 {
    static int delete(PriorityQueue<Integer> pq, HashMap<Integer, Integer> map) { // 유효하지 않은 숫자를 제거하는 함수
        int curNum = 0; // 현재 숫자 초기화
        while (!pq.isEmpty()) {
            curNum = pq.poll(); // 반환값 갱신
            
            int cnt = map.getOrDefault(curNum, 0);
            if (cnt == 0) continue; // 없는 숫자면 continue
            else if (cnt == 1) map.remove(curNum); // 1개면 map에서 제거
            else map.put(curNum, cnt - 1); // 2개 이상이면 개수 감소
            
            break; // 제거했으면 종료
        }
        
        return curNum;
    } // delete 종료
    
    public static void main(String[] args) throws IOException {
        // 입력을 위한 객체 선언
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder(); // 결과값을 저장할 객체
        for (int tc = 0; tc < T; tc++) {
            int k = Integer.parseInt(br.readLine()); // 연산의 개수
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 최소 힙
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙
            HashMap<Integer, Integer> hashMap = new HashMap<>(); // 동기화를 위한 맵
            
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                String op = st.nextToken(); // 연산의 종류
                if (op.equals("I")) { // 삽입 연산
                    int n = Integer.parseInt(st.nextToken()); // 정수
                    minHeap.add(n); // 최소 힙에 add
                    maxHeap.add(n); // 최대 힙에 add
                    hashMap.put(n, hashMap.getOrDefault(n, 0) + 1); // map에 put(중복된 숫자는 value 개수 증가)
                } else { // 삭제 연산
                    String type = st.nextToken(); // 삭제 유형
                    if (hashMap.isEmpty()) continue; // 이중 우선순위 큐가 비어있으면 continue
                    else if (type.equals("1")) delete(maxHeap, hashMap); // 최댓값 삭제
                    else delete(minHeap, hashMap); // 최솟값 삭제
                }
            }
            
            if (hashMap.isEmpty()) sb.append("EMPTY\n"); // 이중 우선순위 큐가 비어있는 경우
            else { // 이중 우선순위 큐가 안 비어있는 경우
                int num = delete(maxHeap, hashMap);
                sb.append(num).append(" "); // 최댓값 추가하기
                if (!hashMap.isEmpty()) num = delete(minHeap, hashMap); // 최솟값 갱신
                sb.append(num).append("\n"); // 최솟값 추가하기
            }
        }
        
        // 버퍼 닫기
        br.close();
        
        // 결과값 출력하기
        System.out.print(sb);
    } // main 종료
} // Main 종료