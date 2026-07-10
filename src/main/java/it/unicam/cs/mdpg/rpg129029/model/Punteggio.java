package it.unicam.cs.mdpg.rpg129029.model;

/**
 * Rappresenta il punteggio che verrà mostrato in classifica se ci si posiziona tra i
 * migliori 5 giocatori.
 */
public class Punteggio implements Comparable<Punteggio>{
        private final String nomeFalconiere;
        private final int missioniCompletate;
        private final int predeCatturate;

        public Punteggio(Falconiere falconiere, int missioniCompletate, int predeCatturate){
            if(falconiere == null) throw new NullPointerException( "Il falconiere non può essere nullo");
                this.nomeFalconiere = falconiere.getNome();
                this.missioniCompletate = missioniCompletate;
                this.predeCatturate = predeCatturate;
        }

        public String getNomeFalconiere() { return nomeFalconiere; }

        public int getMissioniCompletate() { return missioniCompletate; }

        public int getPredeCatturate() { return predeCatturate; }

    /**
     *
     * @param altro l' object da comparare.
     * @return a partià di missioni completate la differenza tra le prede catturate
     */
        @Override
        public int compareTo(Punteggio altro){
            if(this.missioniCompletate != altro.missioniCompletate) {
                return altro.missioniCompletate - this.missioniCompletate;
            }
            else return altro.predeCatturate - this.predeCatturate;
        }
}
