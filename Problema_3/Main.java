import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
  public static void main(String[] args) throws IOException,NumberFormatException,NullPointerException
  {
    //leitura dos dados e controlo da execução do programa

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    int number_cases = Integer.parseInt(input.readLine());
      
    while(number_cases!=0){
      int size = Integer.parseInt(input.readLine());
      City c = new City(size);
      String[] start = input.readLine().split(" ");
      String[] end = input.readLine().split(" ");

      int num_bloq = Integer.parseInt(input.readLine());
     
      for(int n=0; n<num_bloq;n++){
        String[] bloq =input.readLine().split(" ");
        c.addBlockage(Integer.parseInt(bloq[0]),Integer.parseInt(bloq[1]),bloq[2]);
      }
      
      System.out.println(c.numberOfWays(Integer.parseInt(start[0]),Integer.parseInt(start[1]),Integer.parseInt(end[0]),Integer.parseInt(end[1])));

      number_cases--;
    }
  }
}
  
class City {
  /*
    Cria uma cidade com SIZE ruas com orientação Norte/Sul e SIZE ruas com
    orientação Este/Oeste.
  */
  
  //private long [][] w;
  private int s;
  private int[][] horizontal_blocks;
  private int[][] vertical_blocks;

  public City(int size){
    
    this.s = size;
    this.horizontal_blocks=new int[size][size];
    this.vertical_blocks=new int[size][size];
   
  }
  
    /*
    Acrescenta o bloqueio do segmento de rua que tem início no cruzamento
    (X,Y), entre a rua Norte/Sul número X e a rua Este/Oeste número Y, na
    direcção DIRECTION (que pode ser "N", "E", "S" ou "W").
    */
  
  public void addBlockage(int x, int y, String direction){
    if (direction.equals("N")) {
      this.vertical_blocks[x-1][y-1]=1;
    }
    else if (direction.equals("S")) {
      this.vertical_blocks[x-1][y-2]=1;
    }
    else if (direction.equals("E")) {
      this.horizontal_blocks[x-1][y-1]=1;
    }
    else if (direction.equals("W")) {
      this.horizontal_blocks[x-2][y-1]=1;
    }
  }
  
  /*
    Calcula e devolve o número de caminhos que existem, do cruzamento
    (SX,SY) até ao cruzamento (EX,EY), seguindo sempre na direcção Norte
    ou na direcção Este.
  */
  public long numberOfWays(int sx, int sy, int ex, int ey){

    long [][] w = new long[this.s][this.s];

    for(int x = 0; x < this.s; x++){
      for(int y = 0; y < this.s; y++){
          w[x][y] = 0;
      }
    }

    w[ex-1][ey-1]=1;

    for(int i=ex-2;i>=sx-1;i--){
      if(this.horizontal_blocks[i][ey-1]!=1)
        w[i][ey-1]=w[i+1][ey-1];
      
    }

    for(int i=ey-2;i>=sy-1;i--){
      if(this.vertical_blocks[ex-1][i]!=1)
        w[ex-1][i]=w[ex-1][i+1];
    }

    for(int i=ey-2;i>=sy-1;i--){
      for(int j=ex-2;j>=sx-1;j--){
        if (this.horizontal_blocks[j][i]!=1 && this.vertical_blocks[j][i]!=1) {
          w[j][i]=w[j+1][i]+w[j][i+1];
        }
        else if (this.horizontal_blocks[j][i]==1 && this.vertical_blocks[j][i]!=1) {
          w[j][i]=w[j][i+1];
        }
        else if (this.horizontal_blocks[j][i]!=1 && this.vertical_blocks[j][i]==1) {
          w[j][i]=w[j+1][i];
        }
        else
          w[j][i]=0;
      }
    }
    
    // Sem bloqueios
    /*
    for(int x=ex-2;x>=sx-1;x--){
      for(int y=ey-2;y>=sy-1;y--){
        w[x][y]=w[x+1][y]+w[x][y+1];
      }
    }

    for(int i=0;i<w.length;i++){
      for(int d=0;d<w.length;d++){
        System.out.print(w[i][d]);
      }
      System.out.println();
    }
    */
    
    return w[sx-1][sy-1];
  }
}