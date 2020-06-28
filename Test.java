
import java.util.Arrays;

import generator.Bound;
import generator.DataPointGenerator;
import generator.Point;
import generator.QueryGenerator;
import generator.Range;
import rangeTree.RangeTree;
import rangeTree.RangeTreeFC;
import rangeTree.RangeTreeNaive;
import rangeTree.RangeTreeSorted;

public class Test {

	
	public static void main(String[] args) {
		
		Test test = new Test();
//		test.construction_exp();
		test.query_exp1();
//		test.query_exp2();		
	}
	
		
	public void construction_exp() {
			
		double[] l = new double[] {0.1, 0.2, 0.5, 0.8, 1};
		int len = l.length;
		
		int[] points_number= new int[len];
		
		for(int i=0;i<l.length;i++) {
			points_number[i] = (int)(l[i]*Bound.bound);
		}
		
		long[] Naive_Time = new long[len];
		long[] Sorted_Time = new long[len];
		long[] FC_Time = new long[len];
		
		long start = System.currentTimeMillis();
		for(int i=0;i<len;i++) {
			
			System.out.println("***************** Turn "+i+" ****************");
			System.out.println("Generate "+ points_number[i]+" Point Set...");
			DataPointGenerator dpg = new DataPointGenerator();
			Point[] points = dpg.generate_point_set(points_number[i]);
			
			System.out.println("Start Constructing Range Tree Org- Naive");
			RangeTreeNaive naive = new RangeTreeNaive();

			//Create naive object			
			
			start = System.currentTimeMillis();
			//Construct tree
			naive.construct(points);
			Naive_Time[i] = System.currentTimeMillis() - start;
			System.out.println("Naive Construction finished");
			naive = null;
			System.gc();
			
			System.out.println("Start Constructing Range Tree Org- Sorted");
			RangeTreeSorted sorted = new RangeTreeSorted();
			//Create sorted object			
			
			start = System.currentTimeMillis();
			//Construct tree
			sorted.construct(points);

			Sorted_Time[i] = System.currentTimeMillis() - start;
			System.out.println("Sorted Construction finished");
			sorted = null;
			System.gc();
			
			
			System.out.println("Start Constructing Range Tree Org- FC");

			//Create FC object			
			RangeTreeFC fc = new RangeTreeFC();
			start = System.currentTimeMillis();
			//Construct tree
			fc.construct(points);

			FC_Time[i] = System.currentTimeMillis() - start;
			System.out.println("FC Construction finished");
			fc = null;		
			System.gc();		
		}		
		System.out.println("Org Naive Time"+Arrays.toString(Naive_Time));
		System.out.println("Org Sorted Time"+Arrays.toString(Sorted_Time));
		System.out.println("FC Time"+Arrays.toString(FC_Time));		
	}
	
	
	public void query_exp1() {
				
		RangeTree naive = new RangeTreeNaive();
		RangeTree sorted = new RangeTreeSorted();
		RangeTree fc = new RangeTreeFC();
		
		//generate point set
		DataPointGenerator dpg = new DataPointGenerator();
		Point[] points = dpg.generate_point_set(Bound.bound);
		
		
		double[] l = new double[] {0.01, 0.02, 0.05, 0.1, 0.2};
		int len = l.length;
		
		QueryGenerator qg = new  QueryGenerator();
		Range[][] qrs = new Range[5][100];
		
		for(int i=0;i<len;i++) {
			for(int j=0;j<100;j++)
				qrs[i][j] = qg.generate_a_query((int)(l[i]*Bound.bound));
		}


		long[] Time3 = new long[len];
		//create range tree
		System.out.println("Construct:" +fc.getClass().toString());
		fc.construct(points);
		
		long start3 = 0;
		for(int i=0;i<len;i++) {
			System.out.println("***************** Turn "+i+" ****************");
			
			long records = 0;	
			for(int j=0;j<100;j++) {
				//query range
				start3 = System.nanoTime();
				//query
				fc.query(qrs[i][j]);
				records += (System.nanoTime()-start3);
			}
			
			Time3[i] = records/100;
		}
		System.out.println(fc.getClass().toString() + "Time"+Arrays.toString(Time3));
		fc = null;
		System.gc();	
		
		
		long[] Time = new long[len];
		//create range tree
		System.out.println("Construct:" +naive.getClass().toString());
		naive.construct(points);
		
		long start = 0;
		for(int i=0;i<len;i++) {
			System.out.println("***************** Turn "+i+" ****************");
			
			long records = 0;	
			for(int j=0;j<100;j++) {
				//query range
				start = System.nanoTime();
				//query
				naive.query(qrs[i][j]);
				records += (System.nanoTime()-start);
			}
			
			Time[i] = records/100;
		}
		System.out.println(naive.getClass().toString() + "Time"+Arrays.toString(Time));
		naive = null;
		System.gc();	
		
			
		long[] Time2 = new long[len];
		//create range tree
		System.out.println("Construct:" +sorted.getClass().toString());
		sorted.construct(points);
		
		long start2 = 0;
		for(int i=0;i<len;i++) {
			System.out.println("***************** Turn "+i+" ****************");
			
			long records = 0;	
			for(int j=0;j<100;j++) {
				//query range
				start2 = System.nanoTime();
				//query
				sorted.query(qrs[i][j]);
				records += (System.nanoTime()-start2);
			}
			
			Time2[i] = records/100;
		}
		System.out.println(sorted.getClass().toString() + "Time"+Arrays.toString(Time2));
		sorted = null;
		System.gc();	
			
	}


	public void query_exp2() {
		
		int query_range_length = (int)(0.05*Bound.bound);
		
		int[] l = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int len = l.length;
		
		long[] Naive_Time = new long[len];
		long[] Sorted_Time = new long[len];
		long[] FC_Time = new long[len];
		
		long start = 0;	
		for(int i=0;i<len;i++) {
			System.out.println("***************** Turn "+i+" ****************");
			
			//generate set
			DataPointGenerator dpg = new DataPointGenerator();
			Point[] points = dpg.generate_point_set((int)Math.pow(2,l[i])*1000);
			
			System.out.println("Construct Tree-naive...");
			RangeTree naive = new RangeTreeNaive();
			naive.construct(points);
			System.out.println("Construct Tree-sorted...");
			RangeTree sorted = new RangeTreeSorted();
			sorted.construct(points);
			System.out.println("Construct Tree-FC...");
			RangeTree fc = new RangeTreeFC();
			fc.construct(points);
							
			long naive_records = 0;
			long sorted_records = 0;
			long fc_records = 0;
			
			for(int j=0;j<100;j++) {
				QueryGenerator qg = new  QueryGenerator();
				Range qr = qg.generate_a_query(query_range_length);
								
				start = System.currentTimeMillis();
				naive.query(qr);
				naive_records += (System.currentTimeMillis()-start);
							
				start = System.currentTimeMillis();
				sorted.query(qr);
				sorted_records += (System.currentTimeMillis()-start);
								
				start = System.currentTimeMillis();
				fc.query(qr);
				fc_records += (System.currentTimeMillis()-start);		
			}		
			Naive_Time[i] = naive_records;
			Sorted_Time[i] = sorted_records;
			FC_Time[i] = fc_records;
			
		}
		System.out.println("Org Naive Time"+Arrays.toString(Naive_Time));
		System.out.println("Org Sorted Time"+Arrays.toString(Sorted_Time));
		System.out.println("FC Time"+Arrays.toString(FC_Time));
		
		
	}	
	
}
