package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collectors;

public class URLReader {
	public static Vector<String> word = new Vector<String>();
	
	public static int word_count() {
		int count = 0;
		Scanner sc = new Scanner(System.in, "Cp866");
		String url = sc.nextLine();
		sc.close();
		String[] str;
		URL address;
		try {
			address = new URL(url);
			BufferedReader in;
			try {
				in = new BufferedReader(new InputStreamReader(address.openStream(), "UTF-8"));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					str = inputLine.split("(?U)\\n+|\\s+|- +|Ч +|\\.+|\\,+|Х+|Ц+");
					List<String> list = new ArrayList<String>(Arrays.asList(str));
					list.removeAll(Collections.singleton(""));
					for (int i = 0; i < list.size(); i++) {
						word.add(list.get(i).toLowerCase());
					}
				}
				count = word.size();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("“екст не найден");
				System.exit(0);
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			System.out.println("URL-адресс введен некорректно");
			System.exit(0);
		}
		return count;
	}
	public static void frequency(Vector<String> word, int count) {
		Map<String, Integer> hashMap = new HashMap<String, Integer>();
		String[] word1 = new String[count];
		word.copyInto(word1);
		for(int i = 0; i < word1.length; i++) {
			if(!hashMap.containsKey(word1[i])){
				hashMap.put(word1[i], new Integer("1"));
			}
			else{
				hashMap.put(word1[i], hashMap.get(word1[i]) + new Integer(1));
			}
		}
		Map<String, Integer> sortedMap = 
				hashMap.entrySet().stream()
			    .sorted(Entry.<String,Integer>comparingByValue().reversed())
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) -> e1, LinkedHashMap::new));
		
        String[] res_arr = sortedMap.toString().split("\\s+|\\{+|\\}");
		for(int i =0;i<res_arr.length;i++) {
			System.out.println(res_arr[i]);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("¬ведите URL-адрес: ");
		int count = word_count();
		System.out.println(" оличество слов: " + count);
		System.out.print("„астота слов: ");
		frequency(word,count);
	}
}
