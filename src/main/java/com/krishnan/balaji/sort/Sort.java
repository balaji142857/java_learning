package com.krishnan.balaji.sort;

public interface Sort {

	public int[] sort(String name, int[] input);
	
	public default void display(String name,int[] data){
		if(data==null){
			System.out.println(name +": no data");
		}else{
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<Math.min(data.length, 25);i++){
				sb.append(data[i]+", ");
			}
			name = name.substring(name.lastIndexOf(".")+1, name.length());
			System.out.format("%-15s %s \n" , new Object[]{name,sb.toString()});
			
		}
			
	}
	
	public default void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i]=input[j];
		input[j]=temp;
	}
}
