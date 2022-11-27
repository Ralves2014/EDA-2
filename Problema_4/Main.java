import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

class Main {
  public static void main(String[] args) throws IOException,NumberFormatException
  {
    //leitura dos dados e controlo da execução do programa
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    int num_E = Integer.parseInt(input.readLine());
    Organisation o = new Organisation(num_E);

    for(int n=0; n<num_E;n++){
      String[] read_line = input.readLine().split(" ");
      int number_rel=Integer.parseInt(read_line[0]);

      for(int r=1; r<=number_rel;r++)
        o.addFriend(n, Integer.parseInt(read_line[r]));
        
    }

    int number_cases=Integer.parseInt(input.readLine());

    for(int nc=0;nc<number_cases;nc++){
      Boom b = o.firstMaxBoom(Integer.parseInt(input.readLine()));
      if(b.nEmp != 0)
        System.out.println(b.nEmp + " " + b.day);
      else
        System.out.println(0);
    }
  }
}
  
  /*
    Um Boom é constituído pelo número de empregados que recebe a notícia
    num dia e o dia em que isso acontece.
  */
class Boom {
  int nEmp;
  int day;

  public Boom(int nEmp, int d){
    this.nEmp=nEmp;
    this.day=d;
  }
}


  
class Organisation {
  /*
    Cria uma organização com EMPLOYEES funcionários.
  */

  private Graph G;
  public static final int INFINITY = Integer.MAX_VALUE;
  public static final int NONE = -1;
  private static enum Colour{WHITE, GREY, BLACK};
    
  public Organisation(int employees){
    this.G=new Graph(employees);
  }
  
  /*
    Acrescenta o empregado FRIEND aos amigos de EMPLOYEE.
  */
  public void addFriend(int employee, int friend){
    this.G.addEdge(employee, friend);
  }
  
  /*
    Calcula e devolve o Boom correspondente ao primeiro dia em que o
    número de funcionários que recebe a notícia é máximo.
  
    SOURCE é o funcionário que inicia a divulgação da notícia.
  */
  public Boom firstMaxBoom(int source){

    Colour[] colour = new Colour[this.G.nodes];
    int [] d = new int[this.G.nodes];
    int [] p = new int[this.G.nodes];
    int days[]=new int[this.G.nodes];
   

    for(int i=0;i<this.G.nodes;++i){
      colour[i]=Colour.WHITE;
      d[i]=INFINITY;
      p[i]=NONE;
      days[i]=0;
    }
    colour[source]=Colour.GREY;
    d[source]=0;

    Queue<Integer> Q = new LinkedList<>();
    Q.add(source);

    while(!Q.isEmpty()){
      int u = Q.remove();

      for(Integer v : G.adjacents[u]){
        if(colour[v] == Colour.WHITE){
          colour[v] = Colour.GREY;
          d[v] = d[u] + 1;
          p[v]=u;
          days[d[v]]++;
          Q.add(v);
        }
      }
      colour[u]=Colour.BLACK;
    }
      
    int max = 0;
    int day = 0;

    for(int i = 0; i < days.length; i++){
      if(days[i] > max){
        max =  days[i];
        day = i;
      }
    }
        
    return new Boom(max, day);
  }
}


class Graph {
  int nodes;
  List<Integer>[] adjacents; 
  @SuppressWarnings("unchecked")

  public Graph(int nodes){

      this.nodes = nodes;
      adjacents = new List[nodes];
      for (int i = 0; i < nodes; i++){
          adjacents[i] = new LinkedList<>();
      }
  }

  public void addEdge(int u, int v){
    adjacents[u].add(v);
  }
}
