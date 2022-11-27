import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {          
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        //leitura dos dados e controlo da execução do programa
        BufferedReader input= new BufferedReader(new InputStreamReader(System.in));

        int num_coins = Integer.parseInt(input.readLine());
        String value_coins = input.readLine();
        int questions = Integer.parseInt(input.readLine());

        String[] vc = value_coins.split(" ");
        

        /*testes

        System.out.println(Integer.parseInt(num_coins));

        String[] vc = value_coins.split(" ");

        for(int i=0; i<vc.length;i++)
            System.out.println(vc[i]);

        System.out.println(Integer.parseInt(questions));

        */
        
        for(int i=0; i<questions; i++){
            int v = Integer.parseInt(input.readLine());
            Coins c = new Coins(num_coins);
            c.setValues(vc);

            int[] arr = c.change(v);
            System.out.print(v + ": [" + arr[0] + "]");

            for(int h=arr.length-1;h>0;){
                System.out.print(" " + arr[h]);
                h-=arr[h];
            }
            System.out.println();
        }

        input.close();
        
    }
}

class Coins {

    /* Cria um sistema monetário com COINS moedas diferentes. */
    private int c;
    private Integer[] values_coins;

    public Coins(int coins){
        this.c=coins;
    }

    /* Inicializa os valores das moedas existentes. */
    public void setValues(String[] values){
        this.values_coins = new Integer[this.c];

        for(int i=0; i<values.length;i++){
            int num = Integer.parseInt(values[i]);      // passa o numero no formato character to integer
            this.values_coins[i]=num;
        }
    }

    /*
    Calcula e devolve o número mínimo de moedas necessário para
    obter a quantia AMOUNT.
    */
    public int[] change(int amount){

        int[] m = new int[amount+1];
        int[] use_coins = new int[amount+1];
        m[0]=0;

        for(int q=1;q<=amount;q++){
            m[q] = Integer.MAX_VALUE;

            for(int i=0;i<this.values_coins.length;i++){
                
                if(this.values_coins[i]<=q){
                    int v=1+m[q-this.values_coins[i]];

                    if(v<m[q]){
                        m[q]=v;
                        use_coins[q]=this.values_coins[i];
                    }    
                        
                }
            }
        }

        use_coins[0]=m[amount];
        return use_coins;
    }

}
