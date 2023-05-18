import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public enum tipos {
    ACERO("src\\img\\acero.png"),AGUA("src\\img\\agua.png"),BICHO("src\\img\\bicho.png"),DRAGON("src\\img\\dragon.png"),
    ELECTRICO("src\\img\\electrico.png"),FANTASMA("src\\img\\fantasma.png"),FUEGO("src\\img\\fuego.png"),
    HADA("src\\img\\hada.png"),HIELO("src\\img\\hielo.png"),LUCHA("src\\img\\lucha.png"),
    NORMAL("src\\img\\normal.png"),PLANTA("src\\img\\planta.png"),PSIQUICO("src\\img\\psiquico.png"),
    ROCA("src\\img\\roca.png"),SINIESTRO("src\\img\\siniestro.png"),TIERRA("src\\img\\tierra.png"),
    VENENO("src\\img\\veneno.png"),VOLADOR("src\\img\\volador.png"),NULL("null");
    private String path;
    private tipos(String path){
        this.path=path;
    }
    public static JLabel img(tipos enum1){
        if(enum1.getstring()!="null"){
            Icon icon= new ImageIcon(enum1.getstring());
            JLabel label=new JLabel(icon);
            return label;
        }
        return null;
    }
    private String getstring(){
        return path;
    }
    public static tipos gettipo(String string){
        if(string.equals(ACERO.toString())){
            return ACERO;
        }else if(string.equals(AGUA.toString())){
            return AGUA;
        }else if(string.equals(BICHO.toString())){
            return BICHO;
        }else if(string.equals(DRAGON.toString())){
            return DRAGON;
        }else if(string.equals(ELECTRICO.toString())){
            return ELECTRICO;
        }else if(string.equals(FANTASMA.toString())){
            return FANTASMA;
        }else if(string.equals(FUEGO.toString())){
            return FUEGO;
        }else if(string.equals(HADA.toString())){
            return HADA;
        }else if(string.equals(HIELO.toString())){
            return HIELO;
        }else if(string.equals(LUCHA.toString())){
            return LUCHA;
        }else if(string.equals(NORMAL.toString())){
            return NORMAL;
        }else if(string.equals(PLANTA.toString())){
            return PLANTA;
        }else if(string.equals(PSIQUICO.toString())){
            return PSIQUICO;
        }else if(string.equals(ROCA.toString())){
            return ROCA;
        }else if(string.equals(SINIESTRO.toString())){
            return SINIESTRO;
        }else if(string.equals(TIERRA.toString())){
            return TIERRA;
        }else if(string.equals(VENENO.toString())){
            return VENENO;
        }else if(string.equals(VOLADOR.toString())){
            return VOLADOR;
        }else{
            return null;
        }
    }
}
