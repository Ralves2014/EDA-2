import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main{

    public static void main(String[] args) throws IOException{
        
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String str = new String();
        //String[] strings;
        List<Integer> num = new ArrayList<Integer>();

        int children = Integer.parseInt(input.readLine());

        for(int i=1; i<=children;i++){
            str=input.readLine();
            String[] numbers = str.split(" ");

            for(int y=1; y<numbers.length;y++){
                num.add(Integer.parseInt(numbers[y]));
            }
        }

        int max=num.get(0);

        for(int t=1; t<num.size();t++){
            if(num.get(t)>max)
                max=num.get(t);
        }


        System.out.println(max);

    
    }
}