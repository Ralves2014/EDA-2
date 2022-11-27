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
        String[] output = new String[questions];
        
        for(int i=0; i<questions; i++){
            int v = Integer.parseInt(input.readLine());
            Coins c = new Coins(num_coins);
            c.setValues(vc);
            output[i]= v + ": [" + c.change(v) + "]";
        }

        input.close();

        for(int z = 0 ; z<output.length;z++)
            System.out.println(output[z]);

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
    public int change(int amount){

        Integer[] m = new Integer[amount+1];
        m[0]=0;

        for(int q=1;q<=amount;q++){
            m[q] = Integer.MAX_VALUE;

            for(int i=0;i<this.values_coins.length;i++){
                
                if(this.values_coins[i]<=q){
                    int v=1+m[q-this.values_coins[i]];

                    if(v<m[q])    
                        m[q]=v;
                }
            }
        }

        return m[amount];
    }

}
