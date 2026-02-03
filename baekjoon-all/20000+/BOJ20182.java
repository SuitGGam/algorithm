/*
 * 백준 20182번 : 골목 대장 호석 - 효율성 1
 * https://www.acmicpc.net/problem/20182
 * 난이도 : 골드 3
 * 풀이 날짜 : 2026-02-04
 * 간단 설명 : 주어진 돈을 가지고 골목을 이동했을 때 수금액의 최댓값이 최소값인 경우를 구하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BOJ20182 {
	static class Cross { // 교차로의 정보를 담는 class
		int to; // 연결된 교차로
		int cost; // 수금액
		
		Cross(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	} // Cross 종료
	
	static int N; // 교차로의 개수
	static int A, B, C; // 시작 교차로 번호, 도착 교차로 번호, 가진 돈
	static ArrayList<Cross>[] cross; // 교차로 배열
	
	static boolean dijkstra(int limit) { // 골목을 지나는 요금의 최댓값의 최솟값을 구하는 함수
		int[] money = new int[N + 1]; // 지불한 비용 배열
		for (int i = 1; i <= N; i++) money[i] = 2000001; // 비용 초기화
		money[A] = 0; // 시작 교차로 비용 초기화
		
		PriorityQueue<Cross> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost)); // 교차로를 담을 pq
		pq.add(new Cross(A, 0)); // 시작 교차로 add
		
		while (!pq.isEmpty()) {
			Cross cur = pq.poll(); // 현재 교차로
			
			if (money[cur.to] < cur.cost) continue; // 교차로까지의 최소 누적 비용이 현재 누적 비용보다 크면 continue
			
			if (cur.to == B) return true; // 목적지에 도달할 수 있다면 true 반환
			
			for (Cross nxt : cross[cur.to]) {
				if (nxt.cost > limit) continue; // 제한한 금액보다 수금액이 크면 continue
				
				int nxtCost = cur.cost + nxt.cost; // 누적 비용
				if (nxtCost <= C && nxtCost < money[nxt.to]) { // 다음 교차로로 이동이 가능한 경우
					money[nxt.to] = nxtCost; // 비용 갱신
					pq.add(new Cross(nxt.to, nxtCost)); // 다음 교차로 add
				}
			}
		}
		
		return money[B] <= C; // 유효 여부 반환
	} // dijkstra 종료
	
	public static void main(String[] args) throws IOException {
		// 입력을 위한 객체 선언
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 교차로의 개수
		int M = Integer.parseInt(st.nextToken()); // 골목의 개수
		A = Integer.parseInt(st.nextToken()); // 시작 교차로 번호
		B = Integer.parseInt(st.nextToken()); // 도착 교차로 번호
		C = Integer.parseInt(st.nextToken()); // 가지고 있는 돈
		
		cross = new ArrayList[N + 1]; // 교차로 배열
		for (int i = 1; i <= N; i++) cross[i] = new ArrayList<>(); // 교차로 생성
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken()); // 연결 교차로 1
			int v = Integer.parseInt(st.nextToken()); // 연결 교차로 2
			int cost = Integer.parseInt(st.nextToken()); // 수금액
			
			// 교차로 잇기
			cross[u].add(new Cross(v, cost));
			cross[v].add(new Cross(u, cost));
		}
		
		// 버퍼 닫기
		br.close();
		
		int minOfMax = -1; // 골목 요금의 최댓값의 최솟값
		int low = 1; // 최소 수금액
		int high = 20; // 최대 수금액
		while (low <= high) {
			int mid = (low + high) / 2; // 현재 제한 요금
			
			if (dijkstra(mid)) { // mid원 이하의 골목만으로 이동이 가능한 경우
				minOfMax = mid; // 금액 갱신
				high = mid - 1;
			} else low = mid + 1; // mid원 이하의 골목만으로 이동이 불가능한 경우
		}
		
		// 결과값 출력하기
		System.out.print(minOfMax);
	} // main 종료
} // Main 종료
