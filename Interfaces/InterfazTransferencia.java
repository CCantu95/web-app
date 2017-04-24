package interfaces;

import java.sql.Connection;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controles.ControlTransferencia;
import controles.ControlExtraccion;

@WebServlet(urlPatterns = "/Transferencia",
  initParams = {
     @WebInitParam(name = "class", value = "interfaces.InterfazTransferencia")
  }
)
public class InterfazTransferencia extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  HttpSession sesion;
  Connection con;

   ///Redirige cualquier GET recibido a POST
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { doPost(request,response);
   }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    thisRequest = request;
    thisResponse = response;
    String operacion;
    ///La conexion se establece en ContextListener
    con = (Connection) request.getServletContext().getAttribute("DBConnection");

    ///Solo permitir acceso si existe una sesion
    sesion = thisRequest.getSession(false);
    if (sesion == null) ///El usuario no esta logeado
      thisResponse.sendRedirect("index.html");

    operacion = thisRequest.getParameter("operacion");
    if (operacion == null ) ///Es la primera llamada a ExtraerEfectivo (viene de menu.html, con usuario exitosamente logeado)
       solicitarCuentaYMonto();
    else if(operacion.equals("transferir"))
       transferirEfectivo();
    else if (operacion.equals("terminar")){
       RequestDispatcher rd = request.getRequestDispatcher("Logout");
       rd.forward(request, response);
    }

    // HttpSession sesion = thisRequest.getSession(false);
    // String sCuenta =  (String) sesion.getAttribute("cuenta");
    // int ncuenta = Integer.parseInt(sCuenta);
    //
    // ControlExtraccion ce = new ControlExtraccion();
    // float resultado = ce.consultaSaldo(ncuenta, con);


      // response.setContentType("text/html");
      // out = response.getWriter();
      // ///Si la sesion existe, invalidarla
      // HttpSession session = request.getSession(false);
      // if(session != null){
    	//  session.invalidate();
      // }

      // out.println("<!DOCTYPE html> \n" +
      //    "<html> \n" +
      //    "<head> \n" +
      //    "<meta charset=\"utf-8\"> \n" +
      //    "</head>" +
      //    "<body>" +
      //    "<title>Banco AMSS</title> \n" +
      //    "<h2>Cajero Electronico</h2> \n" +
      //    "<h3>Transferencia de Saldo</h3> \n" +
      //    "Esta opcion no esta disponible por el momento.</p> \n"+
      //    "</p> \n" +
      //    "<a href=\"Logout\"> Regresar al inicio de la sesion</a> \n" +
      //    "</body> \n" +
      //    "</html>"
      // );
   }

   private void transferirEfectivo() throws ServletException, IOException {
      int nCuentaOrigen, nCuentaDestino;
      boolean resultado;
      String sCuenta;
      String errorMsg = null;
      String sCuentaOrigen = thisRequest.getParameter("cuenta-origen");
      String sCantidad = thisRequest.getParameter("cantidad");
      String sCuentaDestino = thisRequest.getParameter("cuenta-destino");
      if (sCantidad.length() > 0 && sCuentaOrigen.length() > 0 && sCuentaDestino.length() > 0) {
         float cantidad = Float.valueOf(sCantidad.trim()).floatValue();
         if (cantidad > 0) {
            HttpSession sesion = thisRequest.getSession(false);
            // sCuenta =  (String) sesion.getAttribute("cuenta");
            nCuentaOrigen = Integer.parseInt(sCuentaOrigen);
            nCuentaDestino = Integer.parseInt(sCuentaDestino);

            ControlTransferencia ct = new ControlTransferencia();
            ControlExtraccion ce = new ControlExtraccion();
            resultado = ct.transferirEfectivo(nCuentaOrigen, nCuentaDestino, cantidad, con);
            if (resultado == true) { // Transferencia exitosa
               encabezadoHTML(); // Preparar encabezado de la pagina Web
               float saldoDestino = ce.consultaSaldo(nCuentaDestino, con);
               float saldoOrigen = ce.consultaSaldo(nCuentaOrigen, con);
               out.println(
                  "Operacion exitosa.</p> \n" +
                  "Saldo actual cuenta origen es: " + saldoOrigen +".</p> \n" +
                  "Saldo actual cuenta destino es: " + saldoDestino +".</p> \n" +
                  "<form method=POST action='Transferencia'> \n" +
                     "<input type='hidden' name='operacion' value='terminar'> \n" +
                     "<input type='submit' value='Terminar'></p> \n" +
                   "</form> \n" +
                  "</body> \n" +
                  "</html>"
               );
            }else {
               errorMsg = "Esa cantidad excede a su saldo, o es mayor a lo que puede transferir en una sesion. Por favor indique una cantidad menor.";
            }
         } else {
            errorMsg = "Por favor indique una cantidad mayor que cero.";
         }
      } else {
         errorMsg = "Por favor indique la cuenta de origen, la cantidad y la cuenta de destino.";
      }
      if(errorMsg != null) {
        thisResponse.setContentType("text/html");
        out.println("<h3><font color=red>" + errorMsg + "</font></h3>");
        solicitarCuentaYMonto();
      }
   }

   ///Es importante observar que todas las formas (form) definen la accion POST para
   ///que el metodo doPost sea el que se ejecuta en todos los casos.
    private void solicitarCuentaYMonto() throws ServletException, IOException {
       encabezadoHTML(); ///Preparar encabezado de la pagina Web
       out.println("Indique cuenta y cantidad a extraer, y cuenta a la que se va a depositar</p> \n" +
          "<form method=POST action=Transferencia> \n" +
             "<input type=hidden name=operacion value=transferir> \n" +
             "Cuenta origen: <input type=text name=cuenta-origen size=15 autofocus></p> \n" +
             "Cantidad: <input type=text name=cantidad size=15 autofocus></p> \n" +
             "Cuenta destino: <input type=text name=cuenta-destino size=15 autofocus></p> \n" +
             "<input type=submit value=Enviar name=B1></p> \n" +
          "</form> \n"
       );

       out.println(
          "<form method=POST action=Transferencia> \n" +
             "<input type=hidden name=operacion value=terminar> \n" +
             "<input type=submit value=Cancelar name=B2></p> \n" +
             "</form> \n" +
         "</body>" +
         "</html>"
       );
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
