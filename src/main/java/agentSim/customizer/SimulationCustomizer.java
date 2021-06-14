package agentSim.customizer;

public class SimulationCustomizer {

    private int civilSpeed;
    private int animalSpeed;
    private int medicSpeed;

    private int infectionDuration;
    private int resistanceDuration;

    private int vaccinateFov;
    private int infectionFov;

    public SimulationCustomizer() {
        civilSpeed = 0;
        animalSpeed = 0;
        medicSpeed = 0;
        infectionDuration = 0;
        resistanceDuration = 0;
        vaccinateFov = 0;
        infectionFov = 0;
    }


    public int getInfectionDuration() {
        return infectionDuration;
    }

    public int getResistanceDuration() {
        return resistanceDuration;
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

    public int getVaccinateFov() {
        return vaccinateFov;
    }

    public void setSpeedValues(int cSpeed, int aSpeed, int mSpeed) {
        this.civilSpeed = cSpeed;
        this.animalSpeed = aSpeed;
        this.medicSpeed = mSpeed;
    }

    public void setInteractionValues(int infDur, int resDur) {
        this.infectionDuration = infDur;
        this.resistanceDuration = resDur;
    }

    public int getInfectionFov() {
        return infectionFov;
    }

    public void setInfectionFov(int infectionFov) {
        this.infectionFov = infectionFov;
    }

    public void setVaccinateFov(int fov) {
        this.vaccinateFov = fov;
    }
}
