public class Proyecto{
  private int IDProyecto;
  private String periodo;
  private String campus;
  private String tipo;
  private String institucion_empresa;
  private String fecha_inicio;
  private String fecha_termino;
  private int horas_registradas;
  private String estatus;
  private String carta_finiquito;

  public int getIDProyecto(){
    return IDProyecto;
  }
  public void setIDProyecto(int idp){
    IDProyecto = idp;
  }
  public String getPeriodo(){
    return periodo;
  }
  public void setPeriodo(String p){
    periodo = p;
  }
  public String getCampus(){
    return campus;
  }
  public void setCampus(String c){
    campus = c;
  }
  public String getTipo(){
    return tipo;
  }
  public void setTipo(String t){
    tipo = t;
  }
  public String getInstitucion_Empresa(){
    return institucion_empresa;
  }
  public void setInstitucion_Empresa(String ie){
    institucion_empresa = ie;
  }
  public String getFecha_Inicio(){
    return fecha_inicio;
  }
  public void setFecha_Inicio(String fi){
    fecha_inicio = fi;
  }
  public String getFecha_Termino(){
    return fecha_termino;
  }
  public void setFecha_Termino(String ft){
    fecha_termino = ft;
  }
  public int getHorasRegistradas(){
    return horas_registradas;
  }
  public void setHorasRegistradas(int hr){
    horas_registradas = hr;
  }
  public String getCartaFiniquito(){
    return carta_finiquito;
  }
  public void setCartaFiniquito(String cf){
    carta_finiquito = cf;
  }
}
