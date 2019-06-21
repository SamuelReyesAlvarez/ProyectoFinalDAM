/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.vista;

import dam.DAO.AccesoDAO;
import dam.DAO.GenericDAO;
import dam.MainApp;
import dam.modelo.Acceso;
import dam.modelo.EnvioCorreos;
import dam.modelo.Estadisticas;
import dam.modelo.Estado;
import dam.modelo.JuegoException;
import dam.modelo.Jugador;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorCarga {

    private static final String CORREO_KF = "knight.fight.pi@gmail.com";
    private static final String CLAVE_KF = "!Q2w3e4r5t6y7u8i9o0p";
    private static final String RUTA_IMAGEN = "src/imagenes/foto01.png";
    private static final String NOMBRE_INICIAL = "NuevoJugador";
    private static final int EXP_ACUMULADA_INICIAL = 0;
    private static final int NIVEL_INICIAL = 1;
    private static final int ORO_INICIAL = 0;
    private static final int PUNTOS_NO_USADOS_INICIAL = 20;
    private static final int ESTADISTICAS_INICIALES = 0;
    private static final int POTENCIADO_ESTADO_INICIAL = 0;

    private MainApp mainApp;
    private GenericDAO genericDao;
    private AccesoDAO accesoDao;

    public void setStage(MainApp mainApp) {
        this.mainApp = mainApp;
        genericDao = new GenericDAO(mainApp);
        accesoDao = new AccesoDAO(mainApp);
    }

    public void crearNuevoJugador(Acceso nuevaCuenta) throws JuegoException, IOException {
        //mainApp.configurarYAbrirSesion();

        // Comprobar que el correo introducido no está ya registrado
        Acceso registrar = accesoDao.comprobarCuenta(nuevaCuenta.getCorreo());

        if (registrar == null) {
            // Guardar la nueva cuenta en la base de datos para validar los campos
            genericDao.guardarActualizar(nuevaCuenta);

            EnvioCorreos codigoVerificacion = new EnvioCorreos();
            // Enviar el correo a la dirección introducida por el jugador
            String codigo = codigoVerificacion.enviarCodigoVerificacion(nuevaCuenta.getCorreo(), CORREO_KF, CLAVE_KF);

            if (codigo != null) {
                // Pedir al jugador el código que debe haber recibido por correo
                String[] respuesta = new String[1];
                mainApp.mostrarDialog("Registro Knight Fight", "Confirmación de cuenta",
                        null, "Introduce el código aquí:", respuesta, true);

                if (respuesta != null && respuesta[0].trim().equals(codigo)) {
                    // Crear la partida para el nuevo jugador
                    crearPartida(nuevaCuenta);

                    // Abrir la partida creada
                    mainApp.mostrarPrincipal();
                } else {
                    genericDao.borrar(nuevaCuenta);
                    // Informar que el código introducido no es correcto
                    throw new JuegoException("Codigo incorrecto");
                }
            } else {
                // Informar que no se pudo enviar el correo con el código de verificación
                mainApp.mostrarDialog("Error", "Correo no enviado",
                        "Se produjo un error en el envío del correo de confirmación.", null, null, false);
                genericDao.borrar(nuevaCuenta);
            }
        } else {
            genericDao.borrar(nuevaCuenta);
            // Informar que ya existe una cuenta con el correo introducido
            throw new JuegoException("Correo ya vinculado\na una cuenta");
        }
    }

    public void crearPartida(Acceso nuevaCuenta) throws JuegoException {
        Jugador jugador = new Jugador();
        jugador.setEquipoJugador(new ArrayList<>());
        jugador.setEstadoJugador(new ArrayList<>());
        jugador.setExpAcumulada(EXP_ACUMULADA_INICIAL);
        jugador.setImagen(RUTA_IMAGEN);
        jugador.setNivel(NIVEL_INICIAL);
        jugador.setNombre(NOMBRE_INICIAL);
        jugador.setOroActual(ORO_INICIAL);
        jugador.setPuntosNoUsados(PUNTOS_NO_USADOS_INICIAL);
        jugador.setPuntosCombate(ESTADISTICAS_INICIALES);
        jugador.setTotalRecaudado(ESTADISTICAS_INICIALES);
        jugador.setTareaActiva(new ArrayList<>());

        ArrayList<Estado> estadoJugador = new ArrayList<Estado>();
        Estado estado;

        for (Estado.TipoAtributo tipoEstado : Estado.TipoAtributo.values()) {
            estado = new Estado();
            estado.setTipoAtributo(tipoEstado);
            estadoJugador.add(estado);
            estado.setJugador(jugador);
            estado.setPotenciado(POTENCIADO_ESTADO_INICIAL);
        }

        jugador.setEstadoJugador(estadoJugador);

        Estadisticas estadisticas = new Estadisticas();
        estadisticas.setDerrotas(ESTADISTICAS_INICIALES);
        estadisticas.setMisionesCompletadas(ESTADISTICAS_INICIALES);
        estadisticas.setTotalAtaque(ESTADISTICAS_INICIALES);
        estadisticas.setTotalDefensa(ESTADISTICAS_INICIALES);
        estadisticas.setVictorias(ESTADISTICAS_INICIALES);

        jugador.setEstadisticas(estadisticas);
        nuevaCuenta.setJugador(jugador);
        genericDao.guardarActualizar(nuevaCuenta);

        mainApp.setJugador(nuevaCuenta.getJugador());
    }

    public void cargarPartida(String correo, String clave) {
        try {
            // Comprobar que los datos de acceso son correctos
            Acceso acceder = accesoDao.comprobarCuenta(correo);
            if (acceder != null && acceder.getClave().equals(clave)) {
                mainApp.setJugador(acceder.getJugador());
                mainApp.mostrarPrincipal();
            } else {
                // Informar que se han introducido datos incorrectos para acceder
                // En este caso no se informa de los errores exactos para dificultar
                // el robo de cuentas
                throw new JuegoException("Datos de acceso incorrectos");
            }
        } catch (JuegoException ex) {
            mainApp.mostrarLogin(ex.getMessage());
        }
    }
}
