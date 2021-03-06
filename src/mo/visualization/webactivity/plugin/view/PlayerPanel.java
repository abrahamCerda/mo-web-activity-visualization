package mo.visualization.webactivity.plugin.view;

import mo.core.I18n;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerPanel extends JTabbedPane {

    private JLabel noDataLabel;
    private Map<String, BasePanel> panelsMap;


    /* Los tipos de datos que vienen en el archivo de mapeo a los archivos reales,
    tienen el mismo nombre que las rutas definidas en el plugin de captura, sin considerar el / al inicio!!
     */
    public static final String KEYSTROKES_DATA_TYPE = "keystrokes";
    public static final String MOUSE_MOVES_DATA_TYPE = "mouseMoves";
    public static final String MOUSE_CLICKS_DATA_TYPE = "mouseClicks";
    public static final String MOUSE_UPS_DATA_TYPE = "mouseUps";
    public static final String SEARCHS_DATA_TYPE = "searchs";
    public static final String TABS_DATA_TYPE = "tabs";

    private I18n i18n;


    /* Cuando creamos el panel, definimos los tipos de datos que vamos a mostrar, seteando una lista de subvistas
     * donde cada una corresponde a un tipo de dato en específico, y las agregamos al panel principal del player.
     *
     *
     * Mostraremos cada tipo de dato en una tab correspondiente
     * */
    public PlayerPanel(List<String> dataTypes){
        this.i18n = new I18n(PlayerPanel.class);
        if(dataTypes == null || dataTypes.isEmpty()){
            this.noDataLabel = new JLabel(this.i18n.s("noDataLabelText"));
            this.noDataLabel.setLocation(this.getWidth()/2, this.getHeight()/2);
            this.noDataLabel.setVisible(true);
            return;
        }
        this.panelsMap = this.createPanelsMap(dataTypes);
        for(String dataType : dataTypes){
            this.addTab(this.i18n.s(dataType + "PanelName"), this.panelsMap.get(dataType));
        }
        this.setVisible(true);
    }

    private Map<String, BasePanel> createPanelsMap(List<String> dataTypes){
        Map<String, BasePanel> panelsMap = new HashMap<>();
        for(String dataType : dataTypes){
            BasePanel panel;
            switch(dataType){
                case KEYSTROKES_DATA_TYPE:
                    panel = new KeystrokesPanel();
                    break;
                case MOUSE_MOVES_DATA_TYPE:
                    panel = new MouseMovesPanel();
                    break;
                case MOUSE_CLICKS_DATA_TYPE:
                    panel = new MouseClicksPanel();
                    break;
                case MOUSE_UPS_DATA_TYPE:
                    panel = new MouseUpsPanel();
                    break;
                case SEARCHS_DATA_TYPE:
                    panel = new SearchsPanel();
                    break;
                case TABS_DATA_TYPE:
                    panel = new TabsPanel();
                    break;
                default:
                    panel = null;
                    break;
            }
            panelsMap.put(dataType, panel);
        }
        return panelsMap;
    }

    public void updatePanelData(List<Object> data, String dataType){
        BasePanel panel = this.panelsMap.get(dataType);
        panel.updateData(data);
    }

}
