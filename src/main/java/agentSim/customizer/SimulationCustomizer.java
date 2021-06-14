package agentSim.customizer;

public class SimulationCustomizer {

    private int civilSpeed;
    private int animalSpeed;
    private int medicSpeed;

    public SimulationCustomizer() {
        civilSpeed = 0;
        animalSpeed = 0;
        medicSpeed = 0;
    }

    public int getCivilSpeed() {
        return civilSpeed;
    }

    public int getAnimalSpeed() {
        return animalSpeed;
    }

    public int getMedicSpeed() {
        return medicSpeed;
    }

    public void setSpeedValues(int cSpeed, int aSpeed, int mSpeed) {
        this.civilSpeed = cSpeed;
        this.animalSpeed = aSpeed;
        this.medicSpeed = mSpeed;
    }
}
