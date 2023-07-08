package src;
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
  public static void GravarArquivoBinario(String nomeArquivo, Dados dados) {
    try (FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
      out.writeObject(dados);
      System.out.printf("Objetos serializados e salvos no arquivo %s\n", nomeArquivo);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Desserialização dos dados.
   * 
   * @return dados
   */
  public static Dados LerArquivoBinario(String nomeArquivo) {
    Dados dados = null;

    try (FileInputStream fileIn = new FileInputStream(nomeArquivo);
        ObjectInputStream in = new ObjectInputStream(fileIn)) {
      dados = (Dados) in.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }

    return dados;
  }

}