import java.util.Scanner;

/**
 * @filename      : test1.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年6月16日 上午11:52:01   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public class test1 {
//
//	public static void main(String[] args) {
//	
//	   Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//         
//        int[] a = new int[n];
//        for (int i = 0; i < n; i++) {
//        	a[i]  = sc.nextInt();
//		}
//        int temp = a[0];
//        for (int i = 0; i < a.length; i++) {
//			for (int j = i+1; j < a.length; j++) {
//				if(a[j]<a[i]){
//					temp = a[j];
//					a[j]=a[i];
//					a[i]=temp;
//				}
//			}
//		}
//        
//        int m = sc.nextInt();
//        int[] b = new int[m];
//        for (int i = 0; i < m; i++) {
//        	b[i]  = sc.nextInt();
//		}
//        temp = b[0];
//        for (int i = 0; i < b.length; i++) {
//			for (int j = i+1; j < b.length; j++) {
//				if(b[j]<b[i]){
//					temp = b[j];
//					b[j]=b[i];
//					b[i]=temp;
//				}
//			}
//		}
//        System.out.println("minDifference="+getMinResult(a,b));
//	}
//	
//	public static int getMinResult(int[] a,int[] b){
//		//相减平方和后的值数组
//        int[] delArry = new int[a.length];
//		//平方求和后的的值数组
//        int[] sumArry = new int[b.length-a.length];
//        
//       
//      //第二序列可截取序列长度 n,起始值最大为b.length-a.length
//        for (int i = 0; i < b.length-a.length; i++) {
//			for (int j = 0; j < a.length; j++) {
//				//相减后平方数组
//				delArry[j] = (a[j]-b[i+j])*(a[j]-b[i+j]);
//			}
//			for (int j = 0; j < delArry.length; j++) {
//				//求和后数组
//				sumArry[i] = sumArry[i] + delArry[j];
//			}
//		}
//        //取最小值
//        int min  = sumArry[0];
//        for (int i = 0; i < sumArry.length; i++) {
//        	for (int j = i+1; j < sumArry.length; j++) {
//				if(sumArry[j]<sumArry[i]){
//					min = sumArry[j];
//					sumArry[j] = sumArry[i];
//					sumArry[i] = min;
//				}
//			}
//		}
//        return sumArry[0];
//    }
//    

}
