public class Historial{
  private String id_Historial;
  private int horas_acreditadas;
  private int horas_por_acreditar;
  private Taller_Induccion[] talleres;
  private Proyecto[] proyectos;

  public int calcularHorasAcreditadas(){
    int horasTotales = 0;
    for(int i = 0; i < proyectos.length; i++){
      horasTotales += proyectos[i].getHorasRegistradas();
    }
    return horasTotales;
  }
  public String getID(){
    return id_Historial;
  }
  public void setID(String i){
    id_Historial = i;
  }
  public int getHorasAcreditadas(){
    return calcularHorasAcreditadas();
  }
  public void setHorasAcreditadas(int ha){
    horas_acreditadas = ha;
  }
  public int getHorasPorAcreditar(){
    return 240-calcularHorasAcreditadas();
  }
  public void setHorasPorAcreditar(int hpa){
    horas_por_acreditar = hpa;
  }
  public int getTalleres_Inscritos(){
    return talleres.length;
  }
}
