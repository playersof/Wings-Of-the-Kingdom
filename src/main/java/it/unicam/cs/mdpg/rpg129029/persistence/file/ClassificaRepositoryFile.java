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
import java.util.stream.Collectors;

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


    /**
     * salva(...)
     * Salva solo i 5 migliori punteggi attuali
     * @param punteggio
     */
    @Override
    public void salva(Punteggio punteggio) {
        List<Punteggio> tutti = new ArrayList<>(trovaTutti());
        tutti.add(punteggio);
        List<Punteggio> top5 = tutti.stream()
                        .sorted()
                                .limit(5)
                                        .collect(Collectors.toList());
        scriviGson(top5);
    }

    /**
     * @return tutti i punteggi salvati nel file
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
