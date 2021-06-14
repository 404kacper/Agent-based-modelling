package gui.viewModel;

import agentSim.map.IMap;

import java.util.LinkedList;
import java.util.List;

public class MapViewModel {

    private IMap map;
    private List<SimpleChangeListener<IMap>> mapListeners;

    public MapViewModel() {
        this.mapListeners = new LinkedList<>();
    }

    public void listenToMap(SimpleChangeListener<IMap> listener){
        this.mapListeners.add(listener);
    }

    public void setMapModel(IMap map) {
        this.map = map;
        notifyMapListeners();
    }

    private void notifyMapListeners() {
        for (SimpleChangeListener<IMap> mapListener : mapListeners) {
            mapListener.valueChanged(this.map);
        }
    }
}
