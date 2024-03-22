package Examen;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AdminV {

    public ArrayList<Vehiculo> vehi = new ArrayList();
    private File datos;

    public AdminV(String path) {
        datos = new File(path);
    }

    public ArrayList<Vehiculo> getCarros() {
        return vehi;
    }

    public File getDatos() {
        return datos;
    }

    public void setCarros(ArrayList<Vehiculo> vehi) {
        this.vehi = vehi;
    }

    public void setDatos(File datos) {
        this.datos = datos;
    }

    public void cargarArchivo() {
        try {
            vehi = new ArrayList();
            Vehiculo temp;
            if (datos.exists()) {
                FileInputStream entrada = new FileInputStream(datos);
                ObjectInputStream objeto
                        = new ObjectInputStream(entrada);
                try {
                    while ((temp = (Vehiculo) objeto.readObject()) != null) {
                        vehi.add(temp);
                    }
                } catch (EOFException e) {
                    //encontro el final del archivo
                }
                objeto.close();
                entrada.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void escribirArchivo() {
        FileOutputStream fw = null;
        ObjectOutputStream bw = null;
        try {
            fw = new FileOutputStream(datos);
            bw = new ObjectOutputStream(fw);
            for (Vehiculo u : vehi) {
                bw.writeObject(u);
            }
            bw.flush();
        } catch (Exception ex) {
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception ex) {
            }
        }
    }

}
