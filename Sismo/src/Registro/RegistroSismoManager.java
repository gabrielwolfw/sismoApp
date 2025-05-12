package Registro;

import java.util.ArrayList;
import java.util.List;

public class RegistroSismoManager {
    private List<Sismo> sismos = new ArrayList<>();
    private InteresadoRepository interesadosRepo;
    private NotificadorEmail notificador;

    public RegistroSismoManager(InteresadoRepository repo, NotificadorEmail notificador) {
        this.interesadosRepo = repo;
        this.notificador = notificador;
    }

    // CRUD
    public void crearSismo(Sismo sismo) {
        sismos.add(sismo);
        notificarInteresados(sismo);
    }

    public void editarSismo(int i, Sismo nuevo) {
        if (i >= 0 && i < sismos.size()) sismos.set(i, nuevo);
    }

    public void eliminarSismo(int i) {
        if (i >= 0 && i < sismos.size()) sismos.remove(i);
    }

    public List<Sismo> listarSismos() { return sismos; }

    // Notificaciones por correo
    private void notificarInteresados(Sismo sismo) {
        List<Interesado> aNotificar = interesadosRepo.buscarPorProvincia(sismo.getProvincia());
        String asunto = "Nuevo Sismo registrado en " + sismo.getProvincia();
        String cuerpo = "Estimado usuario,\nSe ha registrado un nuevo sismo:\n\n" + sismo.toString();
        for (Interesado interesado : aNotificar) {
            if (!interesado.getEmail().isEmpty()) {
                notificador.enviarCorreo(interesado.getEmail(), asunto, cuerpo);
            }
        }
    }
}