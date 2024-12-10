package practice;

public class GenericMethodsPractice {
	
	public static void main(String[] args) { // Calling function Caller fucntion
		
		int sum = add(10, 30);
		System.out.println(sum);
		
		int sum1 = add(sum, 60);
		System.out.println(sum1);
	}
	
	public static int add(int a, int b) // called function - generic
	{
		
		int c = a+b;
		return c;
	}
	
	

}
