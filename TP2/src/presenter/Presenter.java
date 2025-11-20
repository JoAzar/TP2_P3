package presenter;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.AgrupacionDeUsuarios;
import model.Grafo;
import model.Usuario;
import view.View;
import view.ViewListener;

public class Presenter implements ViewListener {
	private View _vista;
	private List<Usuario> _usuarios;
	private int _cantGrupos = 2;
	private AgrupacionDeUsuarios _agrupador = new AgrupacionDeUsuarios();
    
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
    	Grafo<Usuario> grafo = crearGrafoCompleto();

    	Map<Integer, List<Usuario>> gruposResultantes = _agrupador.crearComponentesConexas(grafo, _cantGrupos);
    	_vista.mostrarGrupos(gruposResultantes);
    }
    
    // ESTO TENGO QUE VER QUÉ HACE (CAPAZ SE BORRA)
//    public Map<Integer, List<Usuario>> agruparUsuariosPorSimilitud() {
//        Map<Integer, List<Usuario>> gruposDeUsuarios = new HashMap<>();
//        List<Usuario> usuariosPorAgrupar = new ArrayList<>(_usuarios);
//        int grupoId = 1;
//        while (!usuariosPorAgrupar.isEmpty()) {
//            Usuario usuarioBase = usuariosPorAgrupar.remove(0);
//            List<Usuario> grupo = new ArrayList<>();
//            grupo.add(usuarioBase);
//            for(Iterator<Usuario> iter = usuariosPorAgrupar.iterator(); iter.hasNext();) {
//                Usuario usuarioComparado = iter.next();
//                
//                //HAY QUE VER EN EL PDF DEL TP CUÁNTA SIMILARIDAD TIENE QUE TENER UN SUSUARIO CON OTRO DE AHI ES 3 CAMBIARLO POR OTRO VALOR EL MAX ES 16
//                if(usuarioBase.calculoSimilaridad(usuarioComparado) <= 1) {
//                    grupo.add(usuarioComparado);
//                    iter.remove();
//                }
//            }
//            gruposDeUsuarios.put(grupoId++, grupo);
//        }
//        return gruposDeUsuarios;
//    }
//    
    @Override
    public void calcularPromedioInteres() {
        Map<String, Double> promedios = calcularPromedioInteresPorGenero();
        _vista.mostrarPromedioInteres(promedios);
    }
    
    private Map<String, Double> calcularPromedioInteresPorGenero() {
        Map<String, Double> sumas = new HashMap<>();
        Map<String, Integer> conteos = new HashMap<>();
        for(Usuario usuario : _usuarios) {
            for(Map.Entry<String, Integer> entry : usuario.getGustos().entrySet()) {
                String genero = entry.getKey();
                int valor = entry.getValue();
                sumas.put(genero, sumas.getOrDefault(genero, 0.0) + valor);
                conteos.put(genero, conteos.getOrDefault(genero, 0) + 1);
            }
        }
        Map<String, Double> promedios = new HashMap<>();
        for(String genero : sumas.keySet()) {
            promedios.put(genero, sumas.get(genero) / conteos.get(genero));
        }
        return promedios;
    }
    
    @Override
    public void reiniciarSistema() {
    	_usuarios.clear();	
    }
    
	public Grafo<Usuario> crearGrafoCompleto() {
		Grafo<Usuario> grafo = new Grafo<>();
		
	    for(Usuario usuario : _usuarios) {
	        grafo.agregarVertice(usuario);
	    }
	    grafo.crearGrafoCompleto((u1, u2) -> u1.calculoSimilaridad(u2));
	    
		return grafo;
	}

    private Map<String, Integer> mapDeGustos(int tango, int folclore, int rock, int urbano) {
    	Map<String, Integer> gustos = new HashMap<>();
    	gustos.put("tango", tango);
    	gustos.put("folclore", folclore);
    	gustos.put("rock", rock);
    	gustos.put("urbano", urbano);
    	return gustos;
	}
    
    @Override
	public boolean hayUsuariosSuficientes() {
    	if(_usuarios.size() >= 2) return true;
    	return false;
    }
    
	@Override
	public void modificarCantGrupos(String input) {
    	if(input == null || input.trim().isEmpty()) {
    		_cantGrupos = 2;
    		return;
    	}
    	
    	if(!datoIngresadoNoEsNum(input)) {
    		_cantGrupos = 2;
    		return;
    	}
    	
    	int cantGrupos = Integer.parseInt(input);
    	
    	if(!cantGruposEsValido(cantGrupos)){
    		_cantGrupos = 2;
    		return;
    	}
    	
    	_cantGrupos = cantGrupos;
	}
	
    private boolean datoIngresadoNoEsNum(String input) {
        String inputTrim = input.trim();
        boolean esNum = inputTrim.chars().allMatch(Character::isDigit);
        if (!esNum) {
            _vista.mostrarMensaje("Debe ingresar un número entero válido.");
            return false;
        }
        
        return true;
    }
    
    private boolean cantGruposEsValido(int cantGrupos) {
    	int cantUsuarios = 0;
    	if(_usuarios != null) {
    		cantUsuarios = _usuarios.size();
    	}
    	
    	if(cantGrupos < 2) {
    		return false;
    	}
    	if(cantUsuarios > 0 && cantUsuarios < cantGrupos) {
    		return false;
    	}
    	
    	return true;
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
