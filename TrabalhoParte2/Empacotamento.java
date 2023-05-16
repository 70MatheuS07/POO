import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Empacotamento {

  /**
   * Serialização dos dados.
   * 
   * @param dados
   */
  public static void GravarArquivoBinario(Dados dados) {
    try (FileOutputStream fileOut = new FileOutputStream("dados.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
      out.writeObject(dados);
      System.out.println("Objetos serializados e salvos no arquivo dados.ser");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Desserialização dos dados.
   * 
   * @return
   */
  public static Dados LerArquivoBinario() {
    Dados dados = null;

    try (FileInputStream fileIn = new FileInputStream("dados.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn)) {
      dados = (Dados) in.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }

    return dados;
  }

}