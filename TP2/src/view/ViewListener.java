package view;

public interface ViewListener {
	void agregarUsuario(String nombre, int tango, int folklore, int rock, int urbano);
	boolean hayUsuariosSuficientes();
    void ejecutarAlgoritmo();
    void reiniciarSistema();
    boolean sePuedeHabilitarReinicio();

}
