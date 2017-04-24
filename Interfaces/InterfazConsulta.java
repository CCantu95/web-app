package interfaces;

import java.sql.Connection;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controles.ControlExtraccion;

@WebServlet(urlPatterns = "/Consulta",
  initParams = {
     @WebInitParam(name = "class", value = "interfaces.InterfazConsulta")
  }
)
public class InterfazConsulta extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  HttpSession sesion;
  Connection con;

    ///Redirige cualquier GET recibido a POST
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request,response);
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    thisRequest = request;
    thisResponse = response;

    ///La conexion se establece en ContextListener
    con = (Connection) request.getServletContext().getAttribute("DBConnection");

    ///Solo permitir acceso si existe una sesion
    sesion = thisRequest.getSession(false);
    if (sesion == null) ///El usuario no esta logeado
      thisResponse.sendRedirect("index.html");

    HttpSession sesion = thisRequest.getSession(false);
    String sCuenta =  (String) sesion.getAttribute("cuenta");
    int ncuenta = Integer.parseInt(sCuenta);

    ControlExtraccion ce = new ControlExtraccion();
    float resultado = ce.consultaSaldo(ncuenta, con);

    ///Si la sesion existe, invalidarla
    // HttpSession session = request.getSession(false);
    // if(session != null){
    // 	session.invalidate();
    // }
    encabezadoHTML(); ///Preparar encabezado de la pagina Web
    out.println(
       "Operacion exitosa.</p> \n" +
       "Su saldo actual es: " + resultado +".</p> \n" +
       "<form method=POST action='Extraer'> \n" +
          "<input type='hidden' name='operacion' value='terminar'> \n" +
          "<input type='submit' value='Terminar'></p> \n" +
        "</form> \n" +
       "</body> \n" +
       "</html>"
    );
    // out.println("<!DOCTYPE html> \n" +
    //    "<html> \n" +
    //    "<head> \n" +
    //    "<meta charset=\"utf-8\"> \n" +
    //    "</head> \n" +
    //    "<body> \n" +
    //    "<title>Banco AMSS</title> \n" +
    //    "<h2>Cajero Electronico</h2> \n" +
    //    "<h3>Consultar Saldo</h3> \n" +
    //    "Esta opcion no esta disponible por el momento.</p> \n" +
    //    "</p> \n" +
    //    "<a href=\"Logout\"> Regresar al inicio de la sesion</a> \n" +
    //    "</body> \n" +
    //    "</html>"
    // );
  }

  private void encabezadoHTML() throws ServletException, IOException {
     thisResponse.setContentType("text/html");
     out = thisResponse.getWriter();
     ///Preparar el encabezado de la pagina Web de respuesta
     out.println(
        "<!DOCTYPE html> \n" +
        "<html> \n" +
        "<head> \n" +
        "<meta charset=utf-8> \n" +
        "</head> \n" +
        "<body> \n" +
        "<title>Banco AMSS</title> \n" +
        "<h2>Cajero Electronico</h2> \n" +
        "<h3>Consultar saldo</h3>\n"
     );
  }
}
