/*
 * 백준 13335번 : 트럭
 * https://www.acmicpc.net/problem/13335
 * 난이도 : 실버 1
 * 풀이 날짜 : 2025-06-15
 * 간단 설명 : 큐를 이용해 트럭이 다리를 건너게 하는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ13335 {
	// 입력을 위한 객체 선언
	static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken()); // 트럭의 수
		w = Integer.parseInt(st.nextToken()); // 다리의 길이
		L = Integer.parseInt(st.nextToken()); // 다리의 최대 하중
		
		truck = new int[n]; // 트럭 무게 배열의 크기 지정
		st = st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			truck[i] = Integer.parseInt(st.nextToken()); // 트럭 무게 저장
		}
		
		// 버퍼 닫기
		br.close();
		
		minTime = 0; // 최단 시간 초기화
		numTruck = 0; // 다리 위의 트럭 수 초기화
		truckIdx = 0; // 트럭 순서 초기화
		weightSum = 0; // 다리 위의 트럭 무게 합 초기화
		moveTruck(); // 트럭 다리 건너기
		
		// 결과값 출력하기
		System.out.print(minTime);
	} // main 종료
	
	static int n, w, L; // 트럭의 수, 다리의 길이, 다리의 최대 하중
	static int minTime, numTruck, truckIdx, weightSum; // 최단 시간, 다리 위의 트럭 수, 트럭의 입장 순서, 다리 위의 트럭 무게 합
	static int[] truck; // 트럭의 무게를 담을 배열
	static Queue<int[]> queue = new LinkedList<>(); // 다리를 건너는 중인 트럭을 나타내는 큐
	
	// 트럭이 다리를 건너는 함수
	static void moveTruck() {
		// 모든 트럭이 건널 때까지 반복
		while (!(truckIdx == n && queue.size() == 0)) {
			minTime++; // 시간 증가
			
			// 현재 올라가 있는 트럭 한 칸씩 앞으로 이동
			int size = queue.size(); // poll 했을 때 반복이 줄어드는 걸 방지
			for (int i = 0; i < size; i++) {
				int[] truckInfo = queue.poll(); // 트럭 정보 받아오기
				truckInfo[1]++; // 한 칸 앞으로 이동
				// 다리를 다 건넌 경우
				if (truckInfo[1] > w) {
					weightSum -= truckInfo[0]; // 다리 위의 트럭 무게 감소
					numTruck--; // 트럭 수 감소
				}
				// 다리를 다 못 건넌 경우
				else {
					queue.add(truckInfo); // 다시 큐에 담기
				}
			}
			
			// 다리에 트럭이 더 올라갈 수 있는 경우
			if (truckIdx < n && w > numTruck && L >= weightSum + truck[truckIdx]) {
				int[] truckInfo = new int[2]; // 트럭 정보를 담은 배열
				truckInfo[0] = truck[truckIdx]; // 트럭 무게 저장
				truckInfo[1] = 1; // 트럭 위치 저장
				queue.add(truckInfo); // 트럭 정보 큐에 담기
				numTruck++; // 다리 위의 트럭 수 증가
				weightSum += truckInfo[0]; // 다리 위의 트럭 무게 추가
				truckIdx++; // 다음 트럭 대기
			}
		}
		
		// 다 건넜으면 종료
		return;
	}
} // class 종료