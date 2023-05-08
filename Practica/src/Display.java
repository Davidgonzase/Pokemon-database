import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;

import javax.swing.*;

public class Display{
    private JFrame frame;
    private modelo modelo;
    public Display(modelo modelo){
        try {
             for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
               }
            } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        this.modelo=modelo;
        frame=new JFrame("Pokedex");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(720,720);
        inicio();
    }
    public void clean(JPanel panel){
        if(frame.getContentPane()!=null){
            frame.setContentPane(panel);
            SwingUtilities.updateComponentTreeUI(frame);
        }else{
            frame.setContentPane(panel);
        }
    }
    public void inicio(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(62,120,199));

        JButton registerButton = new JButton("Registrar");
        registerButton.setBounds(200,300,100,100);
        registerButton.addActionListener(modelo);
        mainPanel.add(registerButton);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(400,300,100,100);
        buscarButton.addActionListener(modelo);
        mainPanel.add(buscarButton);

        clean(mainPanel);
    }
    public void registraren(){
        JPanel mainPanel = new JPanel();
        mainPanel.setSize(720,720);
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(62,120,199));

        JButton goBack = new JButton();
        goBack.setBounds(0,0,30,30);
        goBack.setIcon(new ImageIcon("src\\flechaizquierda.png"));
        mainPanel.add(goBack);
        goBack.setActionCommand("Tyback");
        goBack.addActionListener(modelo);

        JTextField entrenadorTfield = generateTextField(50,"Nombre del entrenador");
        mainPanel.add(entrenadorTfield);

        JLabel entrenador_errorLabel = generateErrorLabel(80,"");
        mainPanel.add(entrenador_errorLabel);

        String[] nums={"KANTO","JOHTO","HOENN","SINNOH","TESELIA","KALOS","ALOLA","GALAR"};
        JComboBox lvlBox = new JComboBox(nums);
        lvlBox.setSelectedIndex(0);
        lvlBox.setBounds(100, 130, 200, 30);
        lvlBox.setFocusable(false);
        mainPanel.add(lvlBox);

        JLabel label = new JLabel("Region");
        mainPanel.add(label);
        label.setBounds(50, 120, 50, 50);
        label.setForeground(Color.WHITE);
        
        JTextField dniTfield = generateTextField(210,"PokeDni del entrenador");
        mainPanel.add(dniTfield);

        JLabel dni_errorLabel = generateErrorLabel(240,"");
        mainPanel.add(dni_errorLabel);

        dniTfield.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char temp=e.getKeyChar();
                if(!Character.isDigit(temp)){
                    dniTfield.remove(dniTfield.getText().length()-1);
                }
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}     
        });

        entrenador_errorLabel.setVisible(true);
        dni_errorLabel.setVisible(true);

        JLabel label2 = new JLabel("");
        mainPanel.add(label2);
        label2.setBounds(300, 500, 700, 50);
        label2.setForeground(Color.WHITE);

        JButton registerButton = new JButton("Registrarse");
        registerButton.setBounds(300,550,100,100);
        mainPanel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label2.setText("");
                entrenador_errorLabel.setText("");
                dni_errorLabel.setText("");
                if(entrenadorTfield.getText().equals("Nombre del entrenador")){
                    entrenador_errorLabel.setText("Nombre incorrecto");
                }else if(dniTfield.getText().equals("PokeDni del entrenador")){
                    dni_errorLabel.setText("Dni incorrecto");
                }else{
                    label2.setText(modelo.registraren(Integer.parseInt(dniTfield.getText()),entrenadorTfield.getText(),lvlBox.getSelectedIndex()+1));
                };
            
            }

        });

        clean(mainPanel);
    }
    public void registrarpk(){
        JPanel mainPanel = new JPanel();
        mainPanel.setSize(720,720);
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(62,120,199));

        JButton goBack = new JButton();
        goBack.setBounds(0,0,30,30);
        goBack.setIcon(new ImageIcon("src\\flechaizquierda.png"));
        mainPanel.add(goBack);
        goBack.setActionCommand("Tyback");
        goBack.addActionListener(modelo);

        JTextField entrenadorTfield = generateTextField(50,"PokeDNI del entrenador");
        mainPanel.add(entrenadorTfield);
        entrenadorTfield.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char temp=e.getKeyChar();
                if(!Character.isDigit(temp)){
                    entrenadorTfield.remove(entrenadorTfield.getText().length()-1);
                }
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}     
        });

        JLabel entrenador_errorLabel = generateErrorLabel(80,"");
        mainPanel.add(entrenador_errorLabel);

        JTextField nombreTfield = generateTextField(130,"Nombre del Pokemon");
        mainPanel.add(nombreTfield);

        JLabel nombre_errorLabel = generateErrorLabel(160,"");
        mainPanel.add(nombre_errorLabel);

        JTextField apodoTfield = generateTextField(210,"Apodo del pokemon si tiene");
        mainPanel.add(apodoTfield);

        JLabel apodo_errorLabel = generateErrorLabel(240,"");
        mainPanel.add(apodo_errorLabel);

        String[] nums={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100"};
        JComboBox lvlBox = new JComboBox(nums);
        lvlBox.setSelectedIndex(0);
        lvlBox.setBounds(100, 280, 200, 30);
        lvlBox.setFocusable(false);
        mainPanel.add(lvlBox);

        JLabel label = new JLabel("Nivel");
        mainPanel.add(label);
        label.setBounds(50, 270, 50, 50);
        label.setForeground(Color.WHITE);

        entrenador_errorLabel.setVisible(true);
        nombre_errorLabel.setVisible(true);
        apodo_errorLabel.setVisible(true);

        
        JLabel label2 = new JLabel("");
        mainPanel.add(label2);
        label2.setBounds(300, 500, 700, 50);
        label2.setForeground(Color.WHITE);
    
        JButton registerButton = new JButton("Registrarse");
        registerButton.setBounds(300,550,100,100);
        mainPanel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entrenador_errorLabel.setText("");
                label2.setText("");
                nombre_errorLabel.setText("");
                apodo_errorLabel.setText("");
                if(entrenadorTfield.getText().equals("PokeDNI del entrenador")){
                    entrenador_errorLabel.setText("DNI incorrecto");
                }else if(nombreTfield.getText().equals("Nombre del Pokemon")){
                    nombre_errorLabel.setText("Nombre incorrecto");
                }else{
                    String apodo;
                    if(apodoTfield.getText().equals("")||apodoTfield.getText().equals("Apodo del pokemon si tiene")){
                        apodo=null;
                    }else{
                        apodo=apodoTfield.getText();
                    }
                    label2.setText(modelo.registrarpok(Integer.parseInt(entrenadorTfield.getText()),nombreTfield.getText(),apodo,lvlBox.getSelectedIndex()+1));
                }
            }

        });

        clean(mainPanel);
    }
    public void tipo(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(62,120,199));

        JButton goBack = new JButton();
        goBack.setBounds(0,0,30,30);
        goBack.setIcon(new ImageIcon("src//flechaizquierda.png"));
        mainPanel.add(goBack);
        goBack.setActionCommand("Chback");
        goBack.addActionListener(modelo);

        JButton searchPokemonButton = new JButton("Pokemon");
        searchPokemonButton.setBounds(200,300,100,100);
        searchPokemonButton.addActionListener(modelo);
        mainPanel.add(searchPokemonButton);

        JButton searchTrainerButton = new JButton("Entrenador");
        searchTrainerButton.setBounds(400,300,100,100);
        searchTrainerButton.addActionListener(modelo);
        mainPanel.add(searchTrainerButton);
        clean(mainPanel);
    }

    public void busqueda(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(62,120,199));

        JButton goBack = new JButton();
        goBack.setBounds(0,0,30,30);
        goBack.setIcon(new ImageIcon("src\\flechaizquierda.png"));
        goBack.setActionCommand("Tyback");
        goBack.addActionListener(modelo);
        mainPanel.add(goBack);

        JTextField buscador = new JTextField("Escribe aqui lo que buscas");
        buscador.setBounds(40,20,600,30);
        buscador.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (buscador.getText().equals("Escribe aqui lo que buscas")) {
                    buscador.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (buscador.getText().isEmpty()) {
                    buscador.setText("Escribe aqui lo que buscas");
                }
            }
        });
        mainPanel.add(buscador);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.PAGE_AXIS));

        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setBounds(40,60,630,600);
        scrollPane.setPreferredSize(new Dimension(630, 600));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane);

        JButton buscarButton=new JButton();
        buscarButton.setBounds(640,20,30,30);
        buscarButton.setIcon(new ImageIcon("src\\lupa.png"));
        mainPanel.add(buscarButton);
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(modelo.currrentsrch==false){
                    paneles(resultsPanel, modelo.busquedaen(buscador.getText()), scrollPane);
                }else{
                    paneles(resultsPanel, modelo.busquedapk(buscador.getText()), scrollPane);
                }
            }
            
        });

        buscador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(modelo.currrentsrch==false){
                    paneles(resultsPanel, modelo.busquedaen(buscador.getText()), scrollPane);
                }else{
                    paneles(resultsPanel, modelo.busquedapk(buscador.getText()), scrollPane);
                }
            }
            
        });
        clean(mainPanel);
    }

    private JTextField generateTextField(int y, String message){
        JTextField textField = new JTextField(message);
        textField.setBounds(50,y,600,30);
        ponerTextFieldBonito(textField,message);
        return textField;
    }
    private JLabel generateErrorLabel(int y, String message){
        JLabel label = new JLabel(message);
        label.setBounds(50,y,600,30);
        label.setForeground(new Color(250,0,0));
        label.setVisible(false);
        return label;
    }
    private void ponerTextFieldBonito(JTextField textField, String string){
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(string)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(string);
                }
            }
        });
    }
    private void paneles(JPanel panel,String text,JScrollPane scrollPane){
        panel.removeAll();
        int posy=0;
        String parte1="",parte2="";
        boolean permt=false;
        System.out.println(text);
        for(int i=0;i<text.length();i++){
            char temp=text.charAt(i);
            if(temp==','){
                permt=true;
            }else if(temp==';'){
                JButton button = new JButton("Nombre: "+parte1+" ID: "+parte2);
                button.setBackground(Color.white);
                button.setBorderPainted(false);
                button.setBounds(0,posy,630,30);
                button.setFont(new Font("Arial", Font.PLAIN, 30));
                button.setActionCommand(parte2);
                panel.add(button);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(modelo.currrentsrch){
                            pokemon(Integer.parseInt(e.getActionCommand()));
                        }else{
                            String text=modelo.entrenador(Integer.parseInt(e.getActionCommand()));
                            int cont=0;
                            String texttemp="";
                            String nombre="",region="",dni="";
                            for(int i=0;i<text.length();i++){
                                char temp=text.charAt(i);
                                if(temp==','){
                                    if(cont==0){
                                        nombre=texttemp;
                                        texttemp="";
                                        cont++;
                                    }else if(cont==1){
                                        dni=texttemp;
                                        texttemp="";
                                        cont++;
                                    }
                                
                                }else if(temp==';'){
                                    region=texttemp;
                                    break;
                                }else{
                                    texttemp+=temp;
                                }
                            }
                            ResultTrainer(nombre, dni, region);
                        }
                    }
                });
                posy+=31;
                parte1="";
                parte2="";
                permt=false;
            }else{
                if(permt){
                    parte2+=temp;
                }else{
                    parte1+=temp;
                }
            }
            
        }
        if (panel.getPreferredSize().height > scrollPane.getViewport().getSize().height) {
            int newHeight = panel.getPreferredSize().height + 50;
            int currentWidth = scrollPane.getViewport().getViewSize().width;
            scrollPane.setPreferredSize(new Dimension(currentWidth, newHeight));
            scrollPane.revalidate();
            SwingUtilities.updateComponentTreeUI(frame);
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }
    public void ResultPokemon(String nombre,String apodo,String tipo1,String tipo2,String dnitrainer){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(62,120,199));


        JButton goBack = new JButton();
        goBack.setBounds(0,0,30,30);
        goBack.setIcon(new ImageIcon("src\\flechaizquierda.png"));
        mainPanel.add(goBack);
        goBack.setActionCommand("Scback");
        goBack.addActionListener(modelo);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setBounds(40,150,630,300);
        resultsPanel.setLayout(null);
        mainPanel.add(resultsPanel);

        JLabel nombrelabel=createLabel(30,60,"Nombre",20);
        resultsPanel.add(nombrelabel);

        JLabel nombrePoke=createLabel(30,100,nombre,30);
        resultsPanel.add(nombrePoke);

        JLabel regionJLabel=createLabel(30,150,"Region:",20);
        resultsPanel.add(regionJLabel);

        JLabel apodoPoke=createLabel(30,190,apodo,30);
        resultsPanel.add(apodoPoke);

        JLabel tipo1label=createLabel(250,60,"Tipo1:",20);
        resultsPanel.add(tipo1label);

        JLabel tipo1Poke=createLabel(250,100,tipo1,25);
        resultsPanel.add(tipo1Poke);

        JLabel tipo2label=createLabel(460,60,"Tipo2:",20);
        resultsPanel.add(tipo2label);

        JLabel tipo2Poke=createLabel(460,100,tipo2,25);
        resultsPanel.add(tipo2Poke);

        JLabel DNItainer=createLabel(250,150,"Id pokemon:",20);
        resultsPanel.add(DNItainer);

        JLabel pokeDni=createLabel(250,190,dnitrainer,30);
        resultsPanel.add(pokeDni);

        clean(mainPanel);
    }

    public void ResultTrainer(String nombre, String dni, String region){

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(720,720);
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(62,120,199));

        JButton goBack = new JButton();
        goBack.setBounds(0,0,30,30);
        goBack.setIcon(new ImageIcon("src\\flechaizquierda.png"));
        mainPanel.add(goBack);
        goBack.setActionCommand("Scback");
        goBack.addActionListener(modelo);

        JPanel panel = new JPanel();
        panel.setBounds(40,60,630,300);
        panel.setLayout(null);
        mainPanel.add(panel);

        JLabel nombrelabel=createLabel(30,60,"Nombre:",20);
        panel.add(nombrelabel);

        JLabel nombreTrainer=createLabel(30,100,nombre,30);
        panel.add(nombreTrainer);

        JLabel pokeDNI=createLabel(30,150,"PokeDNI:",20);
        panel.add(pokeDNI);

        JLabel DNItrainer=createLabel(30,190,dni,30);
        panel.add(DNItrainer);

        JLabel regionlabel=createLabel(300,60,"Región:",20);
        panel.add(regionlabel);

        JLabel regionTrainer=createLabel(300,100,region,30);
        panel.add(regionTrainer);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setBackground(Color.white);
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.PAGE_AXIS));

        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setBounds(40,360,630,300);
        scrollPane.setPreferredSize(new Dimension(630, 300));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panelpok(resultsPanel, modelo.pokemons(Integer.parseInt(dni)), scrollPane);
        mainPanel.add(scrollPane);

        clean(mainPanel);
    }

    private JLabel createLabel(int posx, int posy, String texto, int tamaño){
        JLabel label=new JLabel(texto);
        label.setBounds(posx,posy,250,40);
        label.setFont(new Font("Arial", Font.PLAIN, tamaño));
        return label;
    }

    private void panelpok(JPanel panel,String text,JScrollPane scrollPane){
        panel.removeAll();
        int posy=0;
        String parte1="",parte2="",parte3="";
        int j=0;
        System.out.println(text);
        for(int i=0;i<text.length();i++){
            char temp=text.charAt(i);
            if(temp==','){
                j++;
            }else if(temp==';'){
                JButton button = new JButton("<html>Nombre: "+parte1+" ID: "+parte2+"<br>Apodo:"+parte3+"<html>");
                button.setBackground(Color.white);
                button.setBorderPainted(false);
                button.setBounds(0,posy,630,30);
                button.setFont(new Font("Arial", Font.PLAIN, 30));
                button.setActionCommand(parte2);
                panel.add(button);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        pokemon(Integer.parseInt(e.getActionCommand()));
                    }
                });
                posy+=31;
                parte1="";
                parte2="";
                parte3="";
                j=0;
            }else{
                if(j==0){
                    parte1+=temp;
                }else if(j==1){
                    parte2+=temp;
                }else if(j==2){
                    parte3+=temp;
                }
            }
        }
        if (panel.getPreferredSize().height > scrollPane.getViewport().getSize().height) {
            int newHeight = panel.getPreferredSize().height + 50;
            int currentWidth = scrollPane.getViewport().getViewSize().width;
            scrollPane.setPreferredSize(new Dimension(currentWidth, newHeight));
            scrollPane.revalidate();
            SwingUtilities.updateComponentTreeUI(frame);
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }

    private void pokemon(int id){
        String text=modelo.pokemon(id);
        int cont=0;
        String texttemp="";
        String nombre="",region="",tipo1="",tipo2="",idEntrenador="";
        for(int i=0;i<text.length();i++){
            char temp=text.charAt(i);
            if(temp==','){
                if(cont==0){
                    idEntrenador=texttemp;
                    texttemp="";
                    cont++;
                }else if(cont==1){
                    nombre=texttemp;
                    texttemp="";
                    cont++;
                }else if(cont==2){
                    region=texttemp;
                    texttemp="";
                    cont++;
                }else if(cont==3){
                    tipo1=texttemp;
                    texttemp="";
                    cont++;
                }else if(cont==4){
                    tipo2=texttemp;
                    
                }
            }else if(temp==';'){
                    tipo2=texttemp;
                    break;
                }else{
                    texttemp+=temp;
                    
                }
            }
            ResultPokemon(nombre, region, tipo1, tipo2, idEntrenador);
        }
                            
}