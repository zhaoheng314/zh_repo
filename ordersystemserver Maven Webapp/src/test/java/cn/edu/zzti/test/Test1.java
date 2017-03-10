package cn.edu.zzti.test;

import cn.edu.zzti.common.util.MD5Util;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(MD5Util.getMD5("1"));
		int arr [] = {2, 33, 25, 44, 77, 22, 2 , 17};
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		quickSort(0, arr.length-1, arr);
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void quickSort(int start, int end, int [] arr){
		if(start < end){
			int index = getIndex(start, end, arr);
			quickSort(start, index - 1, arr);
			quickSort(index + 1, end, arr);
			
		}
	}
	
	public static int getIndex(int start, int end, int [] arr){
		int mark = arr[start];
		if(start < end){
			while(start < end && mark < arr[end]){
				end--;
			}
			arr[start] = arr[end];
			while(start < end && mark >= arr[start]){
				start++;
			}
			arr[end] = arr[start];
		}
		arr[start] = mark;
		return start;
	}

}
