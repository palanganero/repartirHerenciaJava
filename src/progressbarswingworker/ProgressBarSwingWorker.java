/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progressbarswingworker;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import static java.lang.Math.log;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ProgressBarSwingWorker {
    
    public JProgressBar progressBar = new JProgressBar();
    Integer count = 1;
    public int k;
    public int nHerederos;
    public int numerodecombinaciones = 0;
    public int numerodecombinacionescombinaciones = 0;
    static Bien combinaciones[][];
    static Bien combinacionesCombinaciones[][][];
    public List<List<Double>> sumasCombinaciones = new ArrayList();
    public List<String> auxiliar = new ArrayList();
    int n = 0;
    //int nn = 0;
    int nCombTotales = 0;
    int nCombCombTotales = 0;
    BigDecimal nCombCombTotalesTotales = BigDecimal.valueOf(0);
    public ArrayList<String> listaHerederos = new ArrayList<>();
    List<List<List<Bien>>> aux1 = new ArrayList<>();
    List<List<List<Bien>>> aux3 = new ArrayList();
    
    JFrame frame1 = new JFrame("Datos");
    
    JFrame frame2 = new JFrame("Bienes");
    JFrame frame = new JFrame("Resultados");
    JPanel panel, panel2;
    JTextField numeroHerederos = new JTextField();
    JTextField numeroBienes = new JTextField();
    JButton siguiente = new JButton("Siguiente");
    JButton siguientePantallaCalculo = new JButton("Siguiente");
    ArrayList<JTextField> nombres = new ArrayList();
    ArrayList<JTextField> valores = new ArrayList();
    
    Bien[] Input_Array;
    
    ProgressPane progressPane = new ProgressPane();
    
    public long factorial(long numero) {
        long factorial = 1;
        while (numero != 0) {
            factorial = factorial * numero;
            numero--;
        }
        return factorial;
    }
    
    public int nCombinaciones(int numero, int orden) {
        long nCombinaciones = factorial(numero) / (factorial(orden) * (factorial(numero - orden)));
        return (int) nCombinaciones;
    }
    
    public BigDecimal factorialBD(BigDecimal numero) {
        BigDecimal factorial = BigDecimal.valueOf(1);
        while (numero != BigDecimal.valueOf(0)) {
            factorial = factorial.multiply(numero);
            numero = numero.subtract(BigDecimal.ONE);
        }
        return factorial;
    }
    
    public BigDecimal nCombinacionesBD(int numero, int orden) {
        BigDecimal nCombinaciones = factorialBD(BigDecimal.valueOf(numero)).divide((factorialBD(BigDecimal.valueOf(orden)).multiply((factorialBD(BigDecimal.valueOf(numero).subtract(BigDecimal.valueOf(orden)))))));
        
        return nCombinaciones;
    }
    static int p = 0;
    static int z = 0;
    
    void CombinationPossible(Bien Input_Array[], Bien Empty_Array[], int Start_Element, int End_Element, int Array_Index, int r) {
        
        if (Array_Index == r) {
            numerodecombinaciones++;
            progressBar.setValue(numerodecombinaciones);
            for (int x = 0; x < r; x++) {
                combinaciones[p][x] = Empty_Array[x];
            }
            p++;
            return;
        }
        
        for (int y = Start_Element; y <= End_Element && End_Element - y + 1 >= r - Array_Index; y++) {
            Empty_Array[Array_Index] = Input_Array[y];
            CombinationPossible(Input_Array, Empty_Array, y + 1, End_Element, Array_Index + 1, r);
        }
    }
    
    void CombinationCombinationPossible(Bien Input_Array[][], Bien Empty_Array[][], int Start_Element, int End_Element, int Array_Index, int r) {
        // Current combination is ready to be printed, print it
        
        nCombCombTotales++;
        progressBar.setValue((int) nCombCombTotales / 1000);
        if (Array_Index == r) {
            
            if (!tieneRepetidos(Empty_Array)) {
                if (checkTodosLosNumeros(Empty_Array)) {
                    
                    for (int x = 0; x < r; x++) {                        
                        combinacionesCombinaciones[z][x] = Empty_Array[x];
                        
                    }
                    z++;
                }
                
            }
            auxiliar = new ArrayList();
            
            return;
        }
        
        for (int y = Start_Element; y <= End_Element && End_Element - y + 1 >= r - Array_Index; y++) {
            Empty_Array[Array_Index] = Input_Array[y];
            CombinationCombinationPossible(Input_Array, Empty_Array, y + 1, End_Element, Array_Index + 1, r);
        }
    }
    
    void numberCombinationCombinationPossible(Bien Input_Array[][], Bien Empty_Array[][], int Start_Element, int End_Element, int Array_Index, int r) {
        
        nCombCombTotales++;
        progressBar.setValue((int) nCombCombTotales / 1000);
        if (Array_Index == r) {
            
            if (!tieneRepetidos(Empty_Array)) {
                //nCombCombTotales++;
                if (checkTodosLosNumeros(Empty_Array)) {
                    numerodecombinacionescombinaciones++;
                    //System.out.println(numerodecombinacionescombinaciones);
                    /*for (int x = 0; x < r; x++) {                        
                        
                    }*/
                }
                
            }
            auxiliar = new ArrayList();
            return;
        }
        
        for (int y = Start_Element; y <= End_Element && End_Element - y + 1 >= r - Array_Index; y++) {
            Empty_Array[Array_Index] = Input_Array[y];
            numberCombinationCombinationPossible(Input_Array, Empty_Array, y + 1, End_Element, Array_Index + 1, r);
        }
    }
    
    public boolean tieneRepetidos(Bien[][] combinacion) {
        
        int counter = 0;
        for (int i = 0; i < combinacion.length; i++) {
            
            for (int j = 0; j < combinacion[i].length; j++) {
                if (combinacion[i][j] != null) {
                    if (estaEnListaAuxiliar(combinacion[i][j].getNombre())) {
                        return true;
                    }
                }
                
            }
            counter++;
        }
        
        return false;
    }
    
    public boolean estaEnListaAuxiliar(String n) {
        
        for (String l : auxiliar) {
            
            if (n.equals(l)) {
                return true;
            }
        }
        auxiliar.add(n);
        return false;
    }
    
    public boolean checkTodosLosNumeros(Bien[][] combinacion) {
        
        int counter = 0;
        for (int i = 0; i < Input_Array.length; i++) //for(int i=0;i<A.size();i++)    
        {
            
            if (!estaEnListaCombinaciones(Input_Array[i].getNombre(), combinacion)) {
                return false;
            }
            counter++;
            
        }
        return true;
    }
    
    public boolean estaEnListaCombinaciones(String n, Bien[][] combinacion) {
        
        int counter = 0;
        for (int i = 0; i < combinacion.length; i++) {
            
            for (int j = 0; j < combinacion[i].length; j++) {
                if (combinacion[i][j] != null) {
                    if (combinacion[i][j].getNombre().equals(n)) {
                        return true;
                    }
                }
                
            }
            counter++;
        }
        return false;
    }
    
    public double minimo(ArrayList<Double> diferencias) {
        double min = diferencias.get(0);
        for (int i = 1; i < diferencias.size(); i++) {
            if (diferencias.get(i) < min) {
                min = diferencias.get(i);
            }
        }
        return min;
    }
    
    public double maximo(ArrayList<Double> diferencias) {
        double max = diferencias.get(0);
        for (int i = 1; i < diferencias.size(); i++) {
            if (diferencias.get(i) > max) {
                max = diferencias.get(i);
            }
        }
        return max;
    }
    
    public void calcularSumasCombinaciones(Bien[][][] output) {
        
        List<List<Double>> auxiliar1 = new ArrayList();
        int counter = 0;
        
        for (int i = 0; i < output.length; i++) {
            
            progressBar.setValue(progressBar.getValue() + counter);
            List<Double> auxiliar2 = new ArrayList();
            
            if (output[i] != null) {
                for (int j = 0; j < output[i].length; j++) {
                    
                    double auxiliar3 = 0;
                    
                    if (output[i][j] != null) {
                        for (int l = 0; l < output[i][j].length; l++) {
                            if (output[i][j][l] != null) {
                                auxiliar3 += output[i][j][l].getValor();
                                
                            }
                            
                        }
                        
                        auxiliar2.add(auxiliar3);
                    }
                    
                }
                
                auxiliar1.add(auxiliar2);
                
            }
            counter++;
        }
        sumasCombinaciones.addAll(auxiliar1);
    }
    
    public static void main(String[] args) {
        new ProgressBarSwingWorker();
    }
    
    public ProgressBarSwingWorker() {
        
        construyePanel();
        
        construyeVentana();
        
        siguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                
                frame1.setVisible(false);
                construyePanel2();
                añadirElementos();
                construyeVentana2();
                k = Integer.parseInt(numeroBienes.getText());
                nHerederos = Integer.parseInt(numeroHerederos.getText());
                for (int l = 1; l <= k; l++) {
                    nCombTotales += nCombinaciones(Integer.parseInt(numeroBienes.getText()), l);
                    
                }
                
                nCombCombTotalesTotales = nCombinacionesBD(nCombTotales, nHerederos);
                //BigDecimal nCombSinRepetidos = nCombCombTotalesTotales.divide();
                //System.out.println("nCombSinRepetidos(las calculadas)"+nCombCombTotalesTotales);
                Input_Array = new Bien[Integer.parseInt(numeroBienes.getText())];
                combinaciones = new Bien[(int) nCombTotales][k];
                //int nn = combinaciones.length;
                
            }
            
        });
        
        siguientePantallaCalculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                
                for (int i = 0; i < nombres.size(); i++) //for(JTextField t:textos)
                {
                    Bien b = new Bien();
                    b.setNombre(nombres.get(i).getText());
                    b.setValor(Double.parseDouble(valores.get(i).getText()));
                    Input_Array[i] = b;
                }
                for (int i = 0; i < Input_Array.length; i++) {
                    
                }
                frame2.setVisible(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(progressPane);
                
                frame.setSize(500, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(false);
                frame.setVisible(true);
                
            }
            
        });
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }
                
            }
        });
    }
    
    void construyePanel() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(2 + 1, 1));
        panel.add(numeroHerederos);
        panel.add(numeroBienes);
        panel.add(siguiente);
        
    }
    
    void construyePanel2() {
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(Integer.parseInt(numeroBienes.getText()) + 1, 2));
        for (int i = 0; i < Integer.parseInt(numeroBienes.getText()); i++) {
            JTextField nombre = new JTextField("", 20);
            JTextField valor = new JTextField("", 20);
            TextPrompt placeholderNombre = new TextPrompt("Nombre", nombre);
            TextPrompt placeholderValor = new TextPrompt("Valor", valor);
            //nombres[i]=nombre;
            //valores[i]=valor;
            nombres.add(nombre);
            valores.add(valor);
        }
        
    }
    
    void construyeVentana() {
        
        frame1.setLayout(new BoxLayout(frame1.getContentPane(), BoxLayout.Y_AXIS));
        frame1.add(panel);
        frame1.pack();
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
    
    void construyeVentana2() {
        frame2.setLayout(new BoxLayout(frame2.getContentPane(), BoxLayout.Y_AXIS));
        frame2.add(panel2);
        frame2.pack();
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
    
    void añadirElementos() {
        
        for (int i = 0; i < Integer.parseInt(numeroBienes.getText()); i++) {
            panel2.add(nombres.get(i));
            panel2.add(valores.get(i));
        }
        panel2.add(siguientePantallaCalculo);
    }
    
    public class ProgressPane extends JPanel {

        //private JProgressBar progressBar;
        private JButton startButton;
        
        private static final int N_ROWS = 8;
        private String[] header = {"Nombre", "Valor"};
        private DefaultTableModel dtm = new DefaultTableModel(null, header) {
            
            @Override
            public Class<?> getColumnClass(int col) {
                return getValueAt(0, col).getClass();
            }
        };
        private JTable table = new JTable(dtm);
        private JScrollPane scrollPane = new JScrollPane(table);
        private JScrollBar vScroll = scrollPane.getVerticalScrollBar();
        private int row;
        private boolean isAutoScroll;
        
        public ProgressPane() {
            
            this.setLayout(new BorderLayout());
            Dimension d = new Dimension(320, N_ROWS * table.getRowHeight());
            table.setPreferredScrollableViewportSize(d);
            
            scrollPane.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            vScroll.addAdjustmentListener(new AdjustmentListener() {
                
                @Override
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    isAutoScroll = !e.getValueIsAdjusting();
                }
            });
            this.add(scrollPane, BorderLayout.CENTER);
            JPanel panel = new JPanel();
            panel.add(new JButton(new AbstractAction("Add Row") {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                }
            }));
            this.add(panel, BorderLayout.SOUTH);
            
            setLayout(new GridBagLayout());
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = -3;
            add(progressBar, gbc);
            startButton = new JButton("Start");
            gbc.gridy = 1;
            add(startButton, gbc);
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startButton.setEnabled(false);
                    doWork();
                }
            });
            
        }
        
        private void addRow(String nombre, double valor) {
            char c = (char) ('A' + row++ % 26);
            dtm.addRow(new Object[]{
                nombre, String.valueOf(valor)
            });
        }
        
        private void addRowSeparator(String texto) {
            char c = (char) ('A' + row++ % 26);
            dtm.addRow(new Object[]{
                texto, texto
            });
        }
        
        public void doWork() {
            
            Worker worker = new Worker();
            worker.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("progress".equals(evt.getPropertyName())) {
                        
                    }
                }
            });
            
            worker.execute();
            
        }
        
        public class Worker extends SwingWorker<Object, Object> {
            
            @Override
            protected void done() {
                startButton.setEnabled(true);
            }
            
            @Override
            protected Object doInBackground() throws Exception {
                
                Bien Empty_Array[] = new Bien[(int) nCombTotales];
                //System.out.println(n);
                progressBar.setMaximum((int) nCombTotales);
                progressBar.setValue(0);
                for (int i = 1; i <= k; i++) {
                    CombinationPossible(Input_Array, Empty_Array, 0, k - 1, 0, i);
                }
                
                progressBar.setValue(0);
                progressBar.setMaximum((nCombCombTotalesTotales.multiply(BigDecimal.valueOf(2)).divide(BigDecimal.valueOf(1000))).intValue());
                //System.out.println("nCombCombTotalesTotales:" + nCombCombTotalesTotales);
                Bien nEmpty_Empty_Array[][] = new Bien[nHerederos][nCombTotales];
                
                numberCombinationCombinationPossible(combinaciones, nEmpty_Empty_Array, 0, combinaciones.length - 1, 0, nHerederos);
                
                //System.out.println("nCombCombTotales(las reales):" + nCombCombTotales);
                System.out.println("numerodecombinacionescombinaciones(las reales):" + numerodecombinacionescombinaciones);
                combinacionesCombinaciones = new Bien[numerodecombinacionescombinaciones][nHerederos][k];
                Bien Empty_Empty_Array[][] = new Bien[nHerederos][nCombTotales];
                CombinationCombinationPossible(combinaciones, Empty_Empty_Array, 0, combinaciones.length - 1, 0, nHerederos);

                /*
                for (int i = 0; i < combinacionesCombinaciones.length; i++) {
                    for (int j = 0; j < combinacionesCombinaciones[i].length; j++) {
                        for (int l = 0; l < combinacionesCombinaciones[i][j].length; l++) {
                            if (combinacionesCombinaciones[i][j][l] != null) {
                                System.out.print(combinacionesCombinaciones[i][j][l].getValor() + "");
                            }

                        }
                        System.out.println("combinacion");
                    }
                    System.out.println("conjunto de combinaciones");
                }*/
                ArrayList<Double> sumasDiferencias = new ArrayList();
                List<List<Double>> masEquilibrados = new ArrayList();
                ArrayList<Integer> indicesMasEquilibrados = new ArrayList();
                
                progressBar.setMaximum(combinacionesCombinaciones.length);
                progressBar.setValue(0);
                //System.out.println(progressBar.getMaximum());
                calcularSumasCombinaciones(combinacionesCombinaciones);
                
                progressBar.setMaximum(progressBar.getMaximum() + sumasCombinaciones.size());
                //System.out.println(progressBar.getMaximum());
                int counter = 0;
                for (List<Double> n : sumasCombinaciones) {
                    
                    progressBar.setValue(progressBar.getValue() + counter);
                    double sumaDiferecias = 0;
                    for (Double n1 : n) {
                        if (n1 != 0) {
                            double diferencia = -(n1 * log(n1));
                            sumaDiferecias += diferencia;
                        }
                        
                    }
                    sumasDiferencias.add(sumaDiferecias);
                    counter++;
                }
                
                progressBar.setMaximum(progressBar.getMaximum() + sumasCombinaciones.size());
                
                counter = 0;
                int i = 0;
                for (List<Double> n : sumasCombinaciones) {
                    
                    progressBar.setValue(progressBar.getValue() + counter);
                    double sumaDiferencias = 0;
                    for (Double n1 : n) {
                        if (n1 != 0) {
                            double diferencia = -(n1 * log(n1));
                            sumaDiferencias += diferencia;
                        }
                        
                    }
                    if (sumaDiferencias == maximo(sumasDiferencias)) {
                        masEquilibrados.add(n);
                        
                        indicesMasEquilibrados.add(i);
                    }
                    i++;
                    counter++;
                }
                
                counter = 0;
                progressBar.setMaximum(progressBar.getMaximum() + indicesMasEquilibrados.size());
                
                for (Integer j : indicesMasEquilibrados) {
                    progressBar.setValue(progressBar.getValue() + counter);
                    List<List<Bien>> aux2 = new ArrayList<>();
                    
                    for (int n = 0; n < combinacionesCombinaciones[j].length; n++) {
                        
                        List<Bien> aux4 = new ArrayList<>();
                        
                        if (combinacionesCombinaciones[j][n] != null) {
                            for (int m = 0; m < combinacionesCombinaciones[j][n].length; m++) {
                                
                                if (combinacionesCombinaciones[j][n][m] != null) {
                                    aux4.add(combinacionesCombinaciones[j][n][m]);
                                }
                                
                            }
                            aux2.add(aux4);
                            
                        }
                        
                    }
                    aux1.add(aux2);
                    counter++;
                    
                }
                
                for (List<List<Bien>> b1 : aux1) {
                    addRowSeparator("--------------------");
                    for (List<Bien> b2 : b1) {
                        for (Bien b3 : b2) {
                            addRow(b3.getNombre(), b3.getValor());
                        }
                        addRowSeparator("");
                    }
                    
                }
                return null;
            }
            
        }
    }
}
