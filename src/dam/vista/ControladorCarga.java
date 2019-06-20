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
import java.util.HashSet;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorCarga {

    private static final String CORREO_KF = "knight.fight.pi@gmail.com";
    private static final String CLAVE_KF = "!Q2w3e4r5t6y7u8i9o0p";
    //private static final int REMITENTE = 1;
    private static final String RUTA_IMAGEN = "src/imagenes/foto01.png";
    private static final String NOMBRE_INICIAL = "NuevoJugador";
    private static final int EXP_ACUMULADA_INICIAL = 0;
    private static final int NIVEL_INICIAL = 1;
    private static final int ORO_INICIAL = 0;
    private static final int PUNTOS_NO_USADOS_INICIAL = 20;
    private static final int ESTADISTICAS_INICIALES = 0;
    private static final int POTENCIADO_ESTADO_INICIAL = 0;

    private GenericDAO genericDao = new GenericDAO();
    private AccesoDAO accesoDao = new AccesoDAO();
    private MainApp mainApp;

    public void setStage(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void crearNuevoJugador(Acceso nuevaCuenta) throws JuegoException, IOException {
        //mainApp.configurarYAbrirSesion();

        Acceso registrar = new Acceso();
        // Comprobar que el correo introducido no está ya registrado
        registrar = accesoDao.comprobarCuenta(nuevaCuenta.getCorreo());

        if (registrar == null) {
            // Guardar la nueva cuenta en la base de datos para validar los campos
            genericDao.guardarActualizar(nuevaCuenta);

            /*
            Acceso cuentaKF = new Acceso();
            // Obtener los datos de la cuenta de gmail del juego
            cuentaKF = (Acceso) genericDao.obtenerPorId(registrar.getClass(), REMITENTE);

            if (cuentaKF != null) {
             */
            EnvioCorreos codigoVerificacion = new EnvioCorreos();
            // Enviar el correo a la dirección introducida por el jugador
            String codigo = codigoVerificacion.enviarCodigoVerificacion(nuevaCuenta.getCorreo(), CORREO_KF, CLAVE_KF);

            if (codigo != null) {
                // Pedir al jugador el código que debe haber recibido por correo
                String respuesta = null;
                mainApp.mostrarDialog("Registro Knight Fight", "Confirmación de cuenta",
                        null, "Introduce el código aquí:", respuesta);

                if (respuesta != null && respuesta.trim().equals(codigo)) {
                    // Crear la partida para el nuevo jugador
                    Jugador jugador = new Jugador();
                    jugador.setEquipoJugador(new HashSet<>());
                    jugador.setEstadoJugador(new HashSet<>());
                    jugador.setExpAcumulada(EXP_ACUMULADA_INICIAL);
                    jugador.setImagen(RUTA_IMAGEN);
                    jugador.setNivel(NIVEL_INICIAL);
                    jugador.setNombre(NOMBRE_INICIAL);
                    jugador.setOroActual(ORO_INICIAL);
                    jugador.setPuntosNoUsados(PUNTOS_NO_USADOS_INICIAL);
                    jugador.setTareaActiva(new ArrayList<>());

                    HashSet<Estado> estadoJugador = new HashSet<Estado>();
                    Estado estado = new Estado();
                    estado.setJugador(jugador);
                    estado.setPotenciado(POTENCIADO_ESTADO_INICIAL);

                    for (Estado.TipoAtributo tipoEstado : Estado.TipoAtributo.values()) {
                        estado.setTipoAtributo(tipoEstado);
                        estadoJugador.add(estado);
                    }

                    jugador.setEstadoJugador(estadoJugador);

                    Estadisticas estadisticas = new Estadisticas();
                    estadisticas.setDerrotas(ESTADISTICAS_INICIALES);
                    estadisticas.setMisionesCompletadas(ESTADISTICAS_INICIALES);
                    estadisticas.setPuntosCombate(ESTADISTICAS_INICIALES);
                    estadisticas.setTotalAtaque(ESTADISTICAS_INICIALES);
                    estadisticas.setTotalDefensa(ESTADISTICAS_INICIALES);
                    estadisticas.setTotalRecaudado(ESTADISTICAS_INICIALES);
                    estadisticas.setVictorias(ESTADISTICAS_INICIALES);

                    jugador.setEstadisticas(estadisticas);

                    genericDao.guardarActualizar(jugador);

                    nuevaCuenta.setJugador(jugador);
                    genericDao.guardarActualizar(nuevaCuenta);

                    mainApp.setJugador(((Acceso) genericDao.obtenerPorId(nuevaCuenta.getClass(),
                            nuevaCuenta.getCorreo())).getJugador());

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
                        "Se produjo un error en el envío del correo de confirmación.", null, null);
                genericDao.borrar(nuevaCuenta);
            }
            /*
            } else {
                // Informar que no se pudo obtener los datos del gmail del juego
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Función no disponible");
                alert.setContentText("Actualmente no se dispone de la información "
                        + "necesaria para llevar a cabo el proceso de registro. "
                        + "Por favor, comunique este fallo al equipo técnico para "
                        + "que procedan a su correción.");

                alert.showAndWait();
                genericDao.borrar(nuevaCuenta);
            }
             */
        } else {
            genericDao.borrar(nuevaCuenta);
            // Informar que ya existe una cuenta con el correo introducido
            throw new JuegoException("Correo ya vinculado\na una cuenta");
        }
    }

    public void cargarPartida(String correo, String clave) throws JuegoException {
        try {
            //mainApp.configurarYAbrirSesion();

            // Comprobar que los datos de acceso son correctos
            Acceso acceder = new Acceso();
            acceder = accesoDao.comprobarCuenta(correo);
            if (acceder != null && acceder.getClave().equals(clave)) {
                Jugador jugador = (Jugador) genericDao.obtenerPorId(
                        Jugador.class, acceder.getJugador().getIdJugador());

                mainApp.setJugador(jugador);
                mainApp.mostrarPrincipal();
            } else {
                // Informar que se han introducido datos incorrectos para acceder
                // En este caso no se informa de los errores exactos para dificultar
                // el robo de cuentas
                throw new JuegoException("Datos de acceso incorrectos");
            }
        } catch (JuegoException ex) {
            mainApp.mostrarLogin("Error al cargar datos");
        }
    }
}
