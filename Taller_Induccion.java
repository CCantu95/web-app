public class Taller_Induccion{
  private String IDTaller;
  private int grupo; //no sé si el grupo sea un sólo número o sea con clave
  private String periodo;
  private String fecha;
  private String campus;
  private String estatus;

  public String getIDTaller(){
    return IDTaller;
  }
  public void setIDTaller(String idt){
    IDTaller = idt;
  }
  public int getGrupo(){
    return grupo;
  }
  public void setGrupo(int g){
    grupo = g;
  }
  public String getPeriodo(){
    return periodo;
  }
  public void setPeriodo(String p){
    periodo = p;
  }
  public String getFecha(){
    return fecha;
  }
  public void setFecha(String f){
    fecha = f;
  }
  public String getCampus(){
    return campus;
  }
  public void setCampus(String c){
    campus = c;
  }
  public String getEstatus(){
    return estatus;
  }
  public void setEstatus(String e){
    estatus = e;
  }
}
