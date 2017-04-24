public class Alumno{
  private String matricula;
  private String nombre;
  private String apellidos;
  private String estatus_Academico;
  private String carrera;
  private String campus;
  private String telefono;
  private String sexo;
  private int unidades_registradas;
  private int unidades_acreditadas;

  public String getMatricula(){
    return matricula;
  }
  public void setMatricula(String m){
    matricula = m;
  }
  public String getName(){
    return nombre;
  }
  public void setName(String n){
    nombre = n;
  }
  public String getApellidos(){
    return apellidos;
  }
  public void setApellidos(String a){
    apellidos = a;
  }
  public String getEstatus(){
    return estatus_Academico;
  }
  public void setEstatus(String e){
    estatus_Academico = e;
  }
  public String getCarrera(){
    return carrera;
  }
  public void setCarrera(String c){
    carrera = c;
  }
  public String getCampus(){
    return campus;
  }
  public void setCampus(String ca){
    campus = ca;
  }
  public String getTelefono(){
    return telefono;
  }
  public void setTelefono(String t){
    telefono = t;
  }
  public String getSexo(){
    return sexo;
  }
  public void setSexo(String s){
    sexo = s;
  }
  public int getUnidadesRegistradas(){
    return unidades_registradas;
  }
  public void setUnidadesRegistradas(int ur){
    unidades_registradas = ur;
  }
  public int getUnidadesAcreditadas(){
    return unidades_acreditadas;
  }
  public void setUnidadesAcreditadas(int ua){
    unidades_acreditadas = ua;
  }
}
