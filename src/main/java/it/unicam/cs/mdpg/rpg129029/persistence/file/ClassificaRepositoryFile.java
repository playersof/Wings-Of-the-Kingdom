package it.unicam.cs.mdpg.rpg129029.persistence.file;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.mdpg.rpg129029.model.Punteggio;
import it.unicam.cs.mdpg.rpg129029.persistence.ClassificaRepository;
import it.unicam.cs.mdpg.rpg129029.persistence.json.GsonProvider;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ClassificaRepositoryFile implements ClassificaRepository {
    private  final Gson gson;
    private final String percorsoFile;
    private final Type tipoLista;

    public ClassificaRepositoryFile(String percorsoFile)
    {
        this.gson = GsonProvider.creaGson();
        this.percorsoFile = percorsoFile;
        this.tipoLista = new TypeToken<List<Punteggio>>(){}.getType();
    }

// Salva
//  - leggi tutto
//  - aggiorni lista
//  - riscrivi file
// trovaTutti
//    leggi file
//    ritorna lista


    /**
     * @param punteggio
     */
    @Override
    public void salva(Punteggio punteggio) {
        // salva funziona da creazione che da aggiornamento:

        List<Punteggio> tutti = new ArrayList<>(trovaTutti());
        //tutti.removeIf(p -> p.getIdFalconiere().equals(punteggio.getIdFalconiere()));

        tutti.add(punteggio);
        scriviGson(tutti);
    }

    /**
     * @return
     */
    @Override
    public List<Punteggio> trovaTutti() {

        File file = new File(percorsoFile);
        if(!file.exists()){
            //prima esecuzione non esiste nessun punteggio salvato
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(percorsoFile)) {
            List<Punteggio> listaLetti = gson.fromJson(reader, tipoLista);
            return listaLetti != null ? listaLetti : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Errore durante la lettura: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void scriviGson(List<Punteggio> listaPunteggi){
        try (Writer writer = new BufferedWriter(new FileWriter(percorsoFile))) {
            gson.toJson(listaPunteggi, writer);
        } catch (IOException e) {
            System.err.println("Errore durante la scrittura: " + e.getMessage());
        }
    }

}
