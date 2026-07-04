package it.unicam.cs.mdpg.rpg129029.model;

public class Punteggio {
        private final String idFalconiere;
        private final String nomeFalconiere;
        private final int missioniCompletate;
        private final int predeCatturate;

        public Punteggio(Falconiere falconiere, int missioniCompletate, int predeCatturate){
            if(falconiere == null) throw new NullPointerException( "Il falconiere non può essere nullo");
                this.idFalconiere = falconiere.getId();
                this.nomeFalconiere = falconiere.getNome();
                this.missioniCompletate = missioniCompletate;
                this.predeCatturate = predeCatturate;
        }

        public String getIdFalconiere() { return idFalconiere; }

        public String getNomeFalconiere() { return nomeFalconiere; }

        public int getMissioniCompletate() { return missioniCompletate; }

        public int getPredeCatturate() { return predeCatturate; }
}
