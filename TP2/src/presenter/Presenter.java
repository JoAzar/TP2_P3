package presenter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.Grafo;
import model.Usuario;
import view.View;
import view.ViewListener;

public class Presenter implements ViewListener {
	private View _vista;
	private List<Usuario> _usuarios;
    
    public void inicializarInterfazPresenter(View vista) {
        _vista = vista;
        _vista.crearListener(this);
        _usuarios = new ArrayList<>();
    }
    
    @Override
    public void agregarUsuarioALaListaDeUsuarios(String nombre, int tango, int folclore, int rock, int urbano) {
        Usuario usuario = new Usuario(nombre, mapDeGustos(tango, folclore, rock, urbano));
        _usuarios.add(usuario);
    }
    
    @Override
    public void ejecutarAlgoritmo() {
    	 Map<Integer, List<Usuario>> gruposDeUsuarios = agruparUsuariosPorSimilitud();
    	    if(gruposDeUsuarios.isEmpty()) {
    	        System.out.println("El mapa de grupos está vacío.");
    	    } else {
    	        System.out.println("El mapa de grupos contiene los siguientes grupos:");
    	        for (Map.Entry<Integer, List<Usuario>> entry : gruposDeUsuarios.entrySet()) {
    	            System.out.println("Grupo " + entry.getKey() + ":");
    	            for (Usuario usuario : entry.getValue()) {
    	                System.out.println("  - " + usuario.getNombre());
    	            }
    	        }
    	    }
    	_vista.mostrarGrupos(gruposDeUsuarios);
    }
    
    public Map<Integer, List<Usuario>> agruparUsuariosPorSimilitud() {
        Map<Integer, List<Usuario>> gruposDeUsuarios = new HashMap<>();
        List<Usuario> usuariosPorAgrupar = new ArrayList<>(_usuarios);
        int grupoId = 1;
        while (!usuariosPorAgrupar.isEmpty()) {
            Usuario usuarioBase = usuariosPorAgrupar.remove(0);
            List<Usuario> grupo = new ArrayList<>();
            grupo.add(usuarioBase);
            for(Iterator<Usuario> iter = usuariosPorAgrupar.iterator(); iter.hasNext();) {
                Usuario usuarioComparado = iter.next();
                
                //ESTAS DOS LINES SON PARA DEBUGGEAR, HAY QUE QUITAR
                int similitud = usuarioBase.calculoSimilaridad(usuarioComparado);
                System.out.println("\nSimilitud entre [" + usuarioBase.getNombre() + "] y [" + usuarioComparado.getNombre() + "] La diferencia de gustos entre usuarios es < " + similitud+" >");
                
                //HAY QUE VER EN EL PDF DEL TP CUÁNTA SIMILARIDAD TIENE QUE TENER UN SUSUARIO CON OTRO DE AHI ES 3 CAMBIARLO POR OTRO VALOR EL MAX ES 16
                if(usuarioBase.calculoSimilaridad(usuarioComparado) <= 1) {
                    grupo.add(usuarioComparado);
                    iter.remove();
                }
            }
            gruposDeUsuarios.put(grupoId++, grupo);
        }
        return gruposDeUsuarios;
    }
    
    @Override
    public void reiniciarSistema() {
    	_usuarios.clear();	
    }
    
	public Grafo crearGrafoCompleto() {
		Grafo grafo = new Grafo();
	    for(Usuario usuario : _usuarios)
	        grafo.agregarVertice(usuario);
	    grafo.crearGrafoCompleto();
		return grafo;
	}

    private Map<String, Integer> mapDeGustos(int tango, int folclore, int rock, int urbano) {
    	Map<String, Integer> gustos = new HashMap<>();
    	gustos.put("tango", tango);
    	gustos.put("folclore", folclore);
    	gustos.put("rock", rock);
    	gustos.put("urbano", urbano);
    	for (Map.Entry<String, Integer> entry : gustos.entrySet()) {	//ESTO ES PARA DEBUGGEAR
            System.out.println("Género: " + entry.getKey() + " - Puntaje: " + entry.getValue());
        }
    	return gustos;
	}
    
    @Override
	public boolean hayUsuariosSuficientes() {
    	if(_usuarios.size() >= 2) return true;
    	return false;
    }
    
    @Override
    public boolean sePuedeHabilitarReinicio() {
    	if(_usuarios.size() == 1) return true;
    	return false;
    }

	@Override
	public List<Usuario> getUsuarios() {
		return _usuarios;
	}
    
}
