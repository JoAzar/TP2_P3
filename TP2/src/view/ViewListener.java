package view;
import java.util.*;
import model.Usuario;

public interface ViewListener {
	void agregarUsuarioALaListaDeUsuarios(String nombre, int tango, int folklore, int rock, int urbano);
	boolean hayUsuariosSuficientes();
	void modificarCantGrupos(String texto);
    void ejecutarAlgoritmo();
    void reiniciarSistema();
    boolean sePuedeHabilitarReinicio();
	List<Usuario> getUsuarios();
	void calcularPromedioInteres();
}
