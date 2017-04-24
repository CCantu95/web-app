package controles;

import entidades.Cuenta;
import java.sql.Connection;

public class ControlTransferencia {
   Cuenta cuenta;

   public ControlTransferencia(){
     cuenta = new Cuenta();
   }

   ///Implementa una regla de negocio;
   ///no se puede transferir mas de $3000 en una transferencia
   public boolean transferirEfectivo(int nCuentaOrigen, int nCuentaDestino, float cantidad, Connection con){
      if(cantidad < 3000.0f) {
           float saldo = cuenta.getSaldo(nCuentaOrigen, con);
           if (saldo > cantidad) {
               saldo = saldo - cantidad;
               cuenta.setSaldo(nCuentaOrigen, saldo, con);

               float saldoDestino = cuenta.getSaldo(nCuentaDestino, con);
               saldoDestino = saldoDestino + cantidad;
               cuenta.setSaldo(nCuentaDestino, saldoDestino, con);
               return true; ///Transaccion aceptada
           } else {
              return false; ///No hay fondos suficientes en la cuenta
           }
      }
      return false; ///Cantidad demasiado alta
   }

   public float consultaSaldo(int ncuenta, Connection con){
      return cuenta.getSaldo(ncuenta, con);
   }
}
