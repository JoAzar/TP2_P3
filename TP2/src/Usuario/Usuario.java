package Usuario;

public class Usuario {
    private int interesTango, interesFolklore, interesRock, interesUrbano;

    public Usuario (int interesTango, int interesFolklore, int interesRock, int interesUrbano){
        this.interesTango = validar(interesTango);
        this.interesFolklore = validar(interesFolklore);
        this.interesRock = validar(interesRock);
        this.interesUrbano = validar(interesUrbano);
    }

    private int validar(int i){
        if(i < 1 || i > 5) {
            System.out.println("⚠ Valor inválido (" + i + "), se asigna 1 por defecto.");
            return 1;
        }
        return i;
    }

    public int getTango() { return interesTango; }
    public int getFolklore() { return interesFolklore; }
    public int getRock() { return interesRock; }
    public int getUrbano() { return interesUrbano; }

    @Override
    public String toString() {
        return "[Tango=" + interesTango + 
               ", Folklore=" + interesFolklore +
               ", Rock=" + interesRock +
               ", Urbano=" + interesUrbano + "]";
    }
}
